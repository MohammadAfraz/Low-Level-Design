package com.demo.bucket;

import com.demo.bucket.model.Request;

public interface RateLimiter {
    void addRequest(Request request);
    void displayRequestStats();
}
