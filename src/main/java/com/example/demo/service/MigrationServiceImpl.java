package com.example.demo.service;

import com.example.demo.data.MigrationJob;
import com.example.demo.repository.MigrationJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class MigrationServiceImpl implements MigrationService {
    @Autowired
    MigrationJobRepository migrationJobRepository;

    public List<MigrationJob> getAllMigrationjobs() {
        return migrationJobRepository.getAllMigrationjobs();
    }

    public List<MigrationJob> getAllInprogressjobs() {
        return migrationJobRepository.getAllInprogressjobs();
    }

    public List<MigrationJob> getAllCompletedjobs() {
        return migrationJobRepository.getAllCompletedjobs();
    }

}
