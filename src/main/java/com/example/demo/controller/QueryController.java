package com.example.demo.controller;

import com.example.demo.aspects.limiter.RateLimitIt;
import com.example.demo.data.MigrationJob;
import com.example.demo.engine.QueryEngine;
import com.example.demo.query.MigrationRequest;
import com.example.demo.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

// single function interface
@RestController
@RequestMapping
public class QueryController {
    @Autowired
    MigrationService migrationService;

    @Autowired
    QueryEngine queryEngine;

    @RateLimitIt
    @RequestMapping(value = "/api/v1/migration-request", method = RequestMethod.POST, produces = "application/json")
    public Callable<ResponseEntity<?>> submitJob(MigrationRequest request) {
        //LOG.info("runQuery {} tenant:{}", request.getScheme(), tenantId);
        return queryEngine.submitRequest(request);
    }

    @RequestMapping(value = "/api/v1/migration-request/{id}", method = RequestMethod.GET, produces = "application/json")
    public Callable<ResponseEntity<?>> checkJobStatus(@PathVariable("id") String id) {
        //LOG.info("runQuery {} tenant:{}", request.getScheme(), tenantId);
        return queryEngine.checkJobStatus(id);
    }


    @GetMapping(path = "/api/v1/migration-request/", produces = "application/json")
    public HttpEntity<List<MigrationJob>> findAllMigrationJobs(){
        List<MigrationJob> allMigrationJobs = migrationService.getAllMigrationjobs();

        return new ResponseEntity<>(allMigrationJobs, HttpStatus.OK);
    }
}
