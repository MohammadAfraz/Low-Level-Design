import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EventBus implements EventBusInterface{
    private HashedExecutor pushToTopicExecutor;
    private HashedExecutor pushToSubsribersExecutor;
    private Map<String, Topic> topics;
    private int maxRetries;
    private int parallelizationDegree;

    public EventBus(int maxRetries, int parallelization){
        this.maxRetries = maxRetries;
        this.parallelizationDegree = parallelization;
        pushToTopicExecutor = new HashedExecutor(parallelizationDegree);
        pushToSubsribersExecutor = new HashedExecutor(parallelizationDegree);
        topics = new HashMap<>();
    }

    public String addTopic(){
        Topic topic = new Topic();
        topics.put(topic.getTopicId(), topic);
        return topic.getTopicId();
    }

    public void removeTopic(String topicId){
        topics.remove(topicId);
    }

    public void registerSubscriber(String subscriberId, String topicId, Function<Event, CompletionStage<Void>> callBack){
        Subscription subscription = new Subscription(subscriberId, topicId, callBack);
        topics.get(topicId).addSubscription(subscriberId, subscription);
    }
    public void deregisterSubscriber(String subscriberId, String topicId){
        topics.get(topicId).removeSubscription(subscriberId);
    }

    public CompletionStage<Void> send(Event event, String topicId){
        return pushToTopicExecutor.execute(topicId, pushToTopic(topicId, event));//Aysnc Push to topic
    }

    private CompletionStage<Void> pushToTopic(String topicId, Event event){
        topics.get(topicId).addEvent(event);
        Collection<Subscription> subscriptions = topics.get(topicId).getSubscriptions();
        return pushToSubscribers(subscriptions, event);
    }
    public CompletionStage<Void> pushToSubscribers(Collection<Subscription> subscriptions, Event event){
        CompletableFuture[] completableFutures = subscriptions.stream().map(subscription -> pushToSubscriber(subscription, event))
                                                                     .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(completableFutures);
    }

    private CompletionStage<Void> pushToSubscriber(Subscription subscription, Event event){
        return pushToSubsribersExecutor.execute(subscription.getSubscriberId(), callbackWithRetry(subscription, event, maxRetries));//Async Push to a Subscriber
    }

    private CompletionStage<Void> callbackWithRetry(Subscription subscription, Event event, int retryCount){
        return subscription.callBack.apply(event).exceptionally((t) -> {
                if (retryCount == 1) {
                    //throw new RetriesExhaustedException("Max retrials completed for subscriber: " + subscription.subscriberId + " & event: " + event.getEventId());
                    pushToTopic(DeadLetterQueueMap.getDeadQueueId(subscription.getTopicId()), event);
                    throw new RetriesExhaustedException(t);
                }
                callbackWithRetry(subscription, event, retryCount - 1);
                return null;
        });
    }

    public int getTopicSize(String topicId){//In Order to verify event landed up in dead-letter-queue
        return topics.get(topicId).getEvents().size();
    }

    public void shutDownExecutors(){
        pushToSubsribersExecutor.shutDown();
        pushToTopicExecutor.shutDown();
    }
}
