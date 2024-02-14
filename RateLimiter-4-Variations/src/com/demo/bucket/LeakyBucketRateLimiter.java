package com.demo.bucket;

import com.demo.bucket.exceptions.TokenNotAvailableException;
import com.demo.bucket.model.Request;
import com.demo.bucket.model.Storage;
import com.demo.bucket.utils.Timer;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class LeakyBucketRateLimiter implements RateLimiter{
    Queue<Request> bucket;
    int capacity;
    Storage storage;
    ExecutorService executorService;
    ScheduledExecutorService scheduledExecutorServiceForLeakage;
    LongAdder acceptedCount, droppedCount;

    //Rate of leaking is given by leakyRequests/duration
    public LeakyBucketRateLimiter(int capacity, int leakyRequests, long duration){
        this.capacity = capacity;
        bucket = new ConcurrentLinkedDeque<>();
        storage = Storage.INSTANCE;
        acceptedCount = new LongAdder();
        droppedCount = new LongAdder();
        executorService = Executors.newCachedThreadPool();
        scheduledExecutorServiceForLeakage = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorServiceForLeakage.scheduleAtFixedRate(() -> leakBucket(leakyRequests), 0, duration, TimeUnit.MILLISECONDS);
    }
    private void leakBucket(int leakyRequests){
        for(int i=0; i<leakyRequests && bucket.size() > 0; i++){
                storage.addRequest(bucket.poll());
        }
    }

    public void addRequest(Request request){
        executorService.submit(() -> {
            synchronized (this) {
                if (bucket.size() == capacity) {
                    droppedCount.increment();
                    throw new TokenNotAvailableException("Bucket has reached maximum capacity!! Request will be dropped.");
                }
                acceptedCount.increment();
                request.setTimestamp(Timer.getCurrentTime());
                bucket.add(request);
            }
        });
    }

    public void displayRequestStats(){
        System.out.println("Total accepted requests: "+acceptedCount+" dropped requests : "+droppedCount);
    }

    public void shutdownExecutors(){
        scheduledExecutorServiceForLeakage.shutdown();
        executorService.shutdown();
    }
}
