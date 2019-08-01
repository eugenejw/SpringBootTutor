package com.example.demo.engine;

import com.example.demo.query.MigrationJobStatusRequest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;
import com.example.demo.query.MigrationRequest;

public interface QueryEngine {
    Callable<ResponseEntity<?>> submitRequest(MigrationRequest request);
    Callable<ResponseEntity<?>> checkJobStatus(MigrationJobStatusRequest request);
}
