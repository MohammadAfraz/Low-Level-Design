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

public class BucketRateLimiter {
    AtomicInteger bucket;
    Storage storage;
    ExecutorService  executorService;
    ScheduledExecutorService scheduledExecutorService;
    LongAdder acceptedCount, droppedCount;
    public BucketRateLimiter(int tokens, long interval){
        bucket = new AtomicInteger();
        storage = new Storage();
        acceptedCount = new LongAdder();
        droppedCount = new LongAdder();
        executorService = Executors.newCachedThreadPool();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> refillBucket(tokens), 0, interval, TimeUnit.MILLISECONDS);
    }
    private void refillBucket(Integer tokens){
        bucket.addAndGet(tokens);
    }

    public void addRequest(Request request){
        executorService.submit(() -> {
            if(bucket.get() == 0){
                droppedCount.increment();
                throw new TokenNotAvailableException("Bucket has not tokens!! com.demo.bucket.model.Request will be dropped.");
            }
            acceptedCount.increment();
            bucket.decrementAndGet();
            request.setTimestamp(Timer.getCurrentTime());
            storage.addRequest(request);
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
