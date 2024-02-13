package com.demo.bucket.model;

import java.util.UUID;

public class Request {
    String id;
    long timestamp;
    public Request(){
        id = UUID.randomUUID().toString();
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
