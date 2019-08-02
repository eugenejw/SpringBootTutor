package com.example.demo.aspects.limiter;

import org.springframework.stereotype.Component;

@Component
public class RateLimiter {

    public boolean rateLimitCheckByCustomerId(String customerId, long timeStamp) {
        // one request per second
        return false;
    }

    public boolean rateLimitCheckByJobId(String jobId, long timeStamp) {
        // one request per second
        return false;
    }
}
