package com.example.demo.aspects.limiter;


import com.example.demo.engine.SubmitRequestTask;
import com.example.demo.exceptions.RateLimitException;
import com.example.demo.model.MigrationJobStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.query.MigrationJobStatusRequest;
import com.example.demo.query.MigrationRequest;


@Aspect
@Component
public class RateLimiterAspect {
    @Autowired private RateLimiter rateLimiter;

    private static final Logger LOG = LoggerFactory.getLogger(RateLimiterAspect.class);

    @Around("@annotation(RateLimitIt)")
    public Object limitMethodCalls(ProceedingJoinPoint pjp) throws Throwable {

        String SUBMIT_MIGRATION = "submitJob";
        String CHECK_STATUS = "checkJobStatus";
        Object retVal;
        String methodName;
        Object[] methodParams = pjp.getArgs();
        methodName = pjp.getSignature().getName();
        boolean rateLimited = false;
        // Object o = pjp.getThis();

        long startTime = System.nanoTime();
        try {
            try {
                if (methodName.equals(SUBMIT_MIGRATION)) {
                    MigrationRequest request = (MigrationRequest) methodParams[0];
                    rateLimited = rateLimiter.rateLimitCheckByCustomerId(request.getCustomerId(), startTime);

                } else if (methodName.equals(CHECK_STATUS)) {
                    String customerId = (String) methodParams[0];
                    rateLimited = rateLimiter.rateLimitCheckByJobId(customerId, startTime);
                }

                if (rateLimited) {
                    throw new RateLimitException("Too many request! One request per second.");
                }

            } catch (RateLimitException e) {
                throw e;
            } catch (Exception e) {
                LOG.error("Failed to check rate for a query. Error: " + e.getMessage());
            }

            retVal = pjp.proceed();

        } catch (Throwable t) {
            throw t;
        }

        return retVal;
    }
}
