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

//    private MigrationJobStatusRequest request;
    private String jobId;

    public CheckJobStatusTask(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public ResponseEntity<?> call() throws Exception {
        if (jobId == null || jobId.length() == 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        LOG.info("Checking migration job status for job: " + jobId);  // TODO get real status

        JobStatusResult result = new JobStatusResult();
        result.setCustomerId(jobId);
        result.setJobStatus("IN-PROGRESS"); // TODO use ENUM

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
