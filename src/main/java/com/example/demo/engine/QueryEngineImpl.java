package com.example.demo.engine;

import com.example.demo.aspects.limiter.RateLimitIt;
import com.example.demo.query.MigrationRequest;
import com.example.demo.query.MigrationJobStatusRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Callable;

@Component
public class QueryEngineImpl implements QueryEngine {

    @RateLimitIt
    public Callable<ResponseEntity<?>> submitRequest(MigrationRequest request) {
        String jobId = java.util.UUID.randomUUID().toString();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        request.setCustomerId(jobId);
        request.setReceivedTime(timeStamp);
        Callable<ResponseEntity<?>> task = new SubmitRequestTask(request);

        return task;

    }

    @RateLimitIt
    public Callable<ResponseEntity<?>> checkJobStatus(MigrationJobStatusRequest request) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        request.setReceivedTime(timeStamp);
        Callable<ResponseEntity<?>> task = new CheckJobStatusTask(request);

        return task;
    }
}
