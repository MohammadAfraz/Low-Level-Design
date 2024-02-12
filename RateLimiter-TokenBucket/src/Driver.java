import com.demo.bucket.model.Request;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static void main(String args[]){
        BucketRateLimiter bucketRateLimiter = new BucketRateLimiter(5, 10);
        for(int i=0; i<100; i++){
            bucketRateLimiter.addRequest(new Request());
        }
        bucketRateLimiter.displayRequestStats();
    }
}
