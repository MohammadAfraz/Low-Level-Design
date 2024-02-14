package com.demo.bucket;

import com.demo.bucket.exceptions.TokenNotAvailableException;
import com.demo.bucket.model.Request;
import com.demo.bucket.model.Storage;
import com.demo.bucket.utils.Timer;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class SlidingWindowRateLimiter implements RateLimiter{
    Queue[] window;
    long startTime;
    AtomicInteger capacity;
    AtomicInteger currentRequests;
    Storage storage;
    ExecutorService executorService;
    ScheduledExecutorService scheduledExecutorServiceForLeakage;
    LongAdder acceptedCount, droppedCount;
    public SlidingWindowRateLimiter(int capacity, int windowSize){
        this.capacity = new AtomicInteger(capacity);
        this.currentRequests = new AtomicInteger();
        window = new ConcurrentLinkedDeque[windowSize];
        storage = Storage.INSTANCE;
        acceptedCount = new LongAdder();
        droppedCount = new LongAdder();
        executorService = Executors.newCachedThreadPool();
        scheduledExecutorServiceForLeakage = Executors.newSingleThreadScheduledExecutor();
        startTime = Timer.getCurrentTime();
        scheduledExecutorServiceForLeakage.scheduleAtFixedRate(() -> removeOlderRequests(), windowSize, 1, TimeUnit.MILLISECONDS);
    }
    private void removeOlderRequests(){
        long currentTime = Timer.getCurrentTime();
        int idx = (int)(currentTime - startTime)%window.length;
        long cutOffTime = currentTime - window.length;
        if(window[idx] != null){
            Queue<Request> queue = window[idx];
            for(Request request : queue){
                if(request.getTimestamp() <= cutOffTime) {
                    window[idx].remove(request);
                    currentRequests.decrementAndGet();
                }
            }
        }
    }

    //Deleting cutOff entries inside addRequest(), before adding Request, requires Sorted data (based on time) structure like Heap
    //Or will require synchronized methods to avoid race condition while adding to Queue, which is a performance bottleneck, hence avoided in Singleton as well.
    public void  addRequest(Request request){
        executorService.submit(() -> {
            synchronized(this) {
                if (currentRequests.intValue() == capacity.intValue()) {
                    droppedCount.increment();
                    throw new TokenNotAvailableException("Window has reached maximum capacity!! Request will be dropped.");
                }
                long currentTime = Timer.getCurrentTime();
                int idx = (int) (currentTime - startTime) % window.length;
                acceptedCount.increment();
                currentRequests.incrementAndGet();
                request.setTimestamp(currentTime);
                if(window[idx] == null){
                    window[idx] = new ConcurrentLinkedDeque();
                }
                window[idx].add(request);
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
