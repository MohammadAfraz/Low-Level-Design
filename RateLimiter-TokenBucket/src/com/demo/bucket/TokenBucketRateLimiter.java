package com.demo.bucket;

import com.demo.bucket.exceptions.TokenNotAvailableException;
import com.demo.bucket.model.Request;
import com.demo.bucket.model.Storage;
import com.demo.bucket.utils.Timer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**When Number of Tokens to refill is equivalent to full capacity, this becomes equivalent to a Fixed Window Rate Limiter.
 * Use lesser tokens as compared to full capacity, in order to use as a TokenBucket.
 */
public class TokenBucketRateLimiter {
    int capacity;
    AtomicInteger bucket;
    Storage storage;
    ExecutorService  executorService;
    ScheduledExecutorService scheduledExecutorService;
    LongAdder acceptedCount, droppedCount;
    public TokenBucketRateLimiter(int capacity, int tokens, long interval){
        this.capacity = capacity;
        bucket = new AtomicInteger();
        storage = Storage.INSTANCE;
        acceptedCount = new LongAdder();
        droppedCount = new LongAdder();
        executorService = Executors.newCachedThreadPool();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> refillBucket(tokens), 0, interval, TimeUnit.MILLISECONDS);
    }
    private void refillBucket(Integer tokens){
        int updatedValue = Math.min(capacity, bucket.get()+tokens);
        bucket.set(updatedValue);
    }

    public void addRequest(Request request){
        executorService.submit(() -> {
            synchronized(this) {
                if (bucket.get() == 0) {
                    droppedCount.increment();
                    throw new TokenNotAvailableException("Bucket has not tokens!! Request will be dropped.");
                }
                acceptedCount.increment();
                bucket.decrementAndGet();
                request.setTimestamp(Timer.getCurrentTime());
                storage.addRequest(request);
            }
        });
    }

    public void displayRequestStats(){
        System.out.println("Total accepted requests: "+acceptedCount+" dropped requests : "+droppedCount);
    }

    public void shutdownExecutors(){
        scheduledExecutorService.shutdown();
        executorService.shutdown();
    }
}
