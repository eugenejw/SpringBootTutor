package com.example.demo.repository;

import com.example.demo.data.MigrationJob;

import java.util.List;

public interface MigrationJobRepository {
    List<MigrationJob> getAllMigrationjobs();
    List<MigrationJob> getAllInprogressjobs();
    List<MigrationJob> getAllCompletedjobs();

}
