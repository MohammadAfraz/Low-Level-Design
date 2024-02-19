package com.demo.eventbus.model;

import com.demo.eventbus.model.Event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Topic {

    String topicId;
    private List<Event> events;
    private Map<String, Subscription> subscriptions;

    public Topic(){
        topicId = UUID.randomUUID().toString();
        events = new CopyOnWriteArrayList<>();
        subscriptions = new ConcurrentHashMap<>();
    }

    public String getTopicId() {
        return topicId;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public Subscription getSubscription(String subscriberId) {
        return subscriptions.get(subscriberId);
    }

    public void addSubscription(String subscriberId, Subscription subscription) {
        this.subscriptions.put(subscriberId, subscription);
    }

    public void removeSubscription(String subcriberId){
        this.subscriptions.remove(subcriberId);
    }

    public Set<String> getSubscribers(){
        return subscriptions.keySet();
    }

    public Collection<Subscription> getSubscriptions(){
        return subscriptions.values();
    }
}
