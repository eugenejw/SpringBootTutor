package com.example.demo.engine;

import com.example.demo.query.MigrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class QueryEngineImpl implements QueryEngine {
    public Callable<ResponseEntity<?>> submitRequest(MigrationRequest request) {
        String jobId = java.util.UUID.randomUUID().toString();
        request.setCustomerId(jobId);
        Callable<ResponseEntity<?>> requestTask = new SubmitRequestTask(request);

        return requestTask;


    }
}
