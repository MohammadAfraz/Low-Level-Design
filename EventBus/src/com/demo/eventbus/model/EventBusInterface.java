package com.demo.eventbus.model;

import com.demo.eventbus.model.Event;
import com.demo.eventbus.model.Subscription;

import java.util.Collection;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public interface EventBusInterface {
    String addTopic();
    void removeTopic(String topicId);
    void registerSubscriber(String subscriberId, String topicId, Function<Event, CompletionStage<Void>> callBack);
    void deregisterSubscriber(String subscriberId, String topicId);
    CompletionStage<Void> send(Event event, String topicId);
    CompletionStage<Void> pushToSubscribers(Collection<Subscription> subscriptions, Event event);
}
