package com.example.demo.repository;

import com.example.demo.data.MigrationJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.service.SimpleMongoConfig;


@Repository
public class MigrationJobRepositoryImpl implements MigrationJobRepository {
    private List<MigrationJob> jobList;
    private static final Logger LOG = LoggerFactory.getLogger(MigrationJobRepositoryImpl.class);
    @Autowired SimpleMongoConfig simpleMongoConfig;

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
        Query query = new Query();

        try {
            LOG.info("MongoDB test: " + simpleMongoConfig.mongoTemplate().getCollectionNames());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobList;
    }
    public List<MigrationJob> getAllInprogressjobs() {
        return jobList;
    };
    public List<MigrationJob> getAllCompletedjobs() {
        return jobList;
    };
}
