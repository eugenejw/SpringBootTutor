package com.example.demo.engine;

import com.example.demo.query.MigrationRequest;
import com.example.demo.query.MigrationJobStatusRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.concurrent.Callable;
import com.example.demo.model.MigrationJobStatus;


public class CheckJobStatusTask implements Callable<ResponseEntity<?>> {
    private static final Logger LOG = LoggerFactory.getLogger(SubmitRequestTask.class);

    private MigrationJobStatusRequest request;

    public CheckJobStatusTask(MigrationJobStatusRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<?> call() throws Exception {
        if (request.getJobId() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        LOG.info("Checking migration job status for job: " + request.getJobId());  // TODO get real status

        JobStatusResult result = new JobStatusResult();
        result.setJobStatus("IN-PROGRESS"); // TODO use ENUM

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
