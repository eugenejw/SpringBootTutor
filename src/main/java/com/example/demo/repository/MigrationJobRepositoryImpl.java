package com.example.demo.repository;

import com.example.demo.data.MigrationJob;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class MigrationJobRepositoryImpl implements MigrationJobRepository {
    private List<MigrationJob> jobList;

    public MigrationJobRepositoryImpl() {
        jobList = new ArrayList<MigrationJob>();
        MigrationJob dummyJob = MigrationJob.builder()
                .jobStatus("COMPLETED")
                .customerId("10086")
                .sourceCluster("Main Cluster")
                .targetCluster("Backup Cluster")
                .indexCountOnSourceCluster(0)
                .indexCountOnTargetCluster(666)
                .build();

        MigrationJob dummyJobTwo = MigrationJob.builder()
                .jobStatus("IN-PROGRESS")
                .customerId("6666")
                .sourceCluster("Main Cluster")
                .targetCluster("Backup Cluster")
                .indexCountOnSourceCluster(156)
                .indexCountOnTargetCluster(16)
                .build();
        jobList.add(dummyJob);
        jobList.add(dummyJobTwo);

    }

    public List<MigrationJob> getAllMigrationjobs() {
        return jobList;
    };
    public List<MigrationJob> getAllInprogressjobs() {
        return jobList;
    };
    public List<MigrationJob> getAllCompletedjobs() {
        return jobList;
    };
}
