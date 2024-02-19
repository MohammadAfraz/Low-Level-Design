package com.demo.eventbus.model;

import com.demo.eventbus.model.Event;

import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class Subscription {
    String subscriberId;
    String topicId;
    Function<Event, CompletionStage<Void>> callBack;

    public Subscription(String subscriberId, String topicId, Function<Event, CompletionStage<Void>> callBack) {
        this.subscriberId = subscriberId;
        this.topicId = topicId;
        this.callBack = callBack;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Function<Event, CompletionStage<Void>> getCallBack() {
        return callBack;
    }

    public void setCallBack(Function<Event, CompletionStage<Void>> callBack) {
        this.callBack = callBack;
    }
}
