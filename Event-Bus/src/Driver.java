import com.demo.eventbus.model.Event;
import com.demo.eventbus.model.Subscriber;
import com.demo.eventbus.utils.DeadLetterQueueMap;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class Driver {
    public static void main(String args[]){
        EventBus eventBus = new EventBus(3, 10);
        String topicId = eventBus.addTopic();
        String deadLetterQueueId = eventBus.addTopic();
        DeadLetterQueueMap.putMapping(deadLetterQueueId, topicId);
        String subscriberId1 = new Subscriber().getSubscriberId();
        CompletableFuture<Void> future1 = new CompletableFuture<>();
        Function<Event, CompletionStage<Void>> callback = (a) -> {
            String eventId = a.getEventId();
            System.out.println("Event successfully received by subscriber via callback!! EventId : " + eventId + " SubscriberId : " + subscriberId1);
            return future1;
        };
        eventBus.registerSubscriber(subscriberId1, topicId, callback);
        Event event1 = new Event("A");
        System.out.println("Event created and Subscriber registered. EventId: "+event1.getEventId()+" SubscriberId: "+subscriberId1);
        eventBus.send(event1, topicId);
        System.out.println();

        String subscriberId2 = new Subscriber().getSubscriberId();
        CompletableFuture<Void> future2 = new CompletableFuture<>();
        future2.completeExceptionally(new RuntimeException());
        Function<Event, CompletionStage<Void>> callbackException = (a) -> {
            System.out.println("Faulty Callback ran little bit then faced exception!! EventId: "+a.getEventId()+" SubscriberId: "+subscriberId2);
            return future2;
        };
        eventBus.registerSubscriber(subscriberId2, topicId, callbackException);
        Event event2 = new Event("B");
        System.out.println("Event created and Subscriber registered. EventId: "+event2.getEventId()+" SubscriberId: "+subscriberId2);
        eventBus.send(event2, topicId);
        System.out.println();
        System.out.println("Number of events in dead-letter-queue: "+eventBus.getTopicSize(deadLetterQueueId));
        eventBus.shutDownExecutors();
    }
}
