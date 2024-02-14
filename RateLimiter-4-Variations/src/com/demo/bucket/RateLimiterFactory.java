package com.demo.bucket;

public class RateLimiterFactory {
    public static RateLimiter getRateLimiter(RateLimiterType rateLimiterType){
        RateLimiter rateLimiter;
        switch(rateLimiterType){
            case TOKEN_BUCKET :{
                //When Number of Tokens to refill is same as the full capacity of bucket, this becomes equivalent to a Fixed Window Rate Limiter.
                //Use lesser tokens as compared to full capacity, in order to use as a TokenBucket.
                rateLimiter = new TokenBucketRateLimiter(10, 5, 5);
                break;
            }
            case LEAKY_BUCKET:{
                rateLimiter = new LeakyBucketRateLimiter(10, 2, 5);
                break;
            }
            case FIXED_WINDOW:{
                rateLimiter = new TokenBucketRateLimiter(10, 10, 5);
                break;
            }
            default:{
                rateLimiter = new SlidingWindowRateLimiter(10, 3);
            }
        }
        return rateLimiter;
    }
}
