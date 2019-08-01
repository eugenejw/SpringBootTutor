package com.example.demo.controller;

import com.example.demo.data.MigrationJob;
import com.example.demo.engine.QueryEngine;
import com.example.demo.modal.Course;
import com.example.demo.modal.dto.CourseDto;
import com.example.demo.query.MigrationRequest;
import com.example.demo.service.CourseService;
import com.example.demo.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

// single function interface
@RestController
@RequestMapping
public class CourseController {
    @Autowired // IOC
    CourseService courseService; // Singleton

    @Autowired
    MigrationService migrationService;

    @Autowired
    QueryEngine queryEngine;

    @GetMapping(path = "/", produces = "application/json")
    public HttpEntity findAllCourses(){
        List<Course> allCourses = courseService.findAllCourses();

        return new ResponseEntity<>(allCourses,HttpStatus.OK);
    }

    @GetMapping(path = "/api/migration/findAllMigrationJobs", produces = "application/json")
    public HttpEntity<List<MigrationJob>> findAllMigrationJobs(){
        List<MigrationJob> allMigrationJobs = migrationService.getAllMigrationjobs();

        return new ResponseEntity<>(allMigrationJobs, HttpStatus.OK);
    }

//    @GetMapping(path = "/api/course/findAllCourses", produces = "application/json")
//    public HttpEntity<List<CourseDto>> findAllCourses(){
//        List<CourseDto> allCourses = courseService.findAllCourses();
//
//        return new ResponseEntity<>(allCourses, HttpStatus.OK);
//    }

    @GetMapping(path = "/look-up/{inputString}", produces = "application/json")
    public HttpEntity<Course> searchCourse(@PathVariable("inputString") String inputString) {

        List<Course> findedCourse = courseService.searchByCourseName(inputString);

        return new ResponseEntity(findedCourse, HttpStatus.OK);
    }

    @RequestMapping(value = "/submit-migration", method = RequestMethod.POST, produces = "application/json")
    public Callable<ResponseEntity<?>> runQuery(MigrationRequest request) {
        //LOG.info("runQuery {} tenant:{}", request.getScheme(), tenantId);
        return queryEngine.submitRequest(request);
    }
}
