import com.demo.bucket.LeakyBucketRateLimiter;
import com.demo.bucket.SlidingWindowRateLimiter;
import com.demo.bucket.TokenBucketRateLimiter;
import com.demo.bucket.model.Request;

public class Driver {
    public static void main(String args[]){
        //TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(5, 5, 10);
        //LeakyBucketRateLimiter leakyBucketRateLimiter = new LeakyBucketRateLimiter(10, 1, 5);
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(10, 3);
        for(int i=0; i<100; i++){
            slidingWindowRateLimiter.addRequest(new Request());
        }
        slidingWindowRateLimiter.displayRequestStats();
    }
}
