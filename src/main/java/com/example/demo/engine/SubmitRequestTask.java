package com.example.demo.engine;

import com.example.demo.query.MigrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.concurrent.Callable;
import com.example.demo.model.MigrationJobStatus;


public class SubmitRequestTask implements Callable<ResponseEntity<?>> {
    private static final Logger LOG = LoggerFactory.getLogger(SubmitRequestTask.class);

    private MigrationRequest request;

    public SubmitRequestTask(MigrationRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<?> call() throws Exception {
        LOG.info("Sending request to message queue...");

        QueryResult result = new QueryResult();
        result.setJobId(request.getCustomerId());
        result.setJobStatus("SUBMITTED"); // TODO use ENUM

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
