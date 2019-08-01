package com.example.demo.service;

import com.example.demo.data.MigrationJob;

import java.util.List;

public interface MigrationService {
    List<MigrationJob> getAllMigrationjobs();
    List<MigrationJob> getAllInprogressjobs();
    List<MigrationJob> getAllCompletedjobs();





}
