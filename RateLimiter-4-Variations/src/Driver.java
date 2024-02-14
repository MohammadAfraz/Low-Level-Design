import com.demo.bucket.*;
import com.demo.bucket.model.Request;

public class Driver {
    public static void main(String args[]){
        RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(RateLimiterType.SLIDING_WINDOW);
        for(int i=0; i<100; i++){
            rateLimiter.addRequest(new Request());
        }
        rateLimiter.displayRequestStats();
    }
}
