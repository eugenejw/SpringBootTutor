package com.example.demo.aspects.limiter;

import org.springframework.stereotype.Component;

@Component
public class RateLimiter {

    public boolean rateLimitCheckByCustomerId(String customerId, long timeStamp) {
        return false;
    }

    public boolean rateLimitCheckByJobId(String jobId, long timeStamp) {
        return false;
    }
}
