package com.example.demo.repository;

import com.example.demo.data.MigrationJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired RedisTestRepository redisTestRepository;

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


    private MigrationJob jobOrmToJobHttp (CrossClusterNameMapping jobOrm) {
        String status = "PREPARING";
        status = jobOrm.isMigrationPreparationFinished() ? "READY" : status;
        status = jobOrm.isMigrationCompleted() ? "COMPLETED" : status;
        MigrationJob job = new MigrationJob().builder()
                .customerId(jobOrm.getTenantID())
                .sourceCluster(jobOrm.getSourceCluster())
                .targetCluster(jobOrm.getTargetCluster())
                .jobStatus(status)
                .build();

        return job;
    }

    public List<MigrationJob> getAllMigrationjobs() {
        Query query = new Query();

        List<MigrationJob> jobs = new ArrayList<>();
        try {
            LOG.info("MongoDB dbs name: " + simpleMongoConfig.mongoTemplate().getCollectionNames());

            List<CrossClusterNameMapping> allJobs = simpleMongoConfig.mongoTemplate().findAll(CrossClusterNameMapping.class, "crossClusterNameMapping");
            LOG.info("Doc size: " + allJobs.size());
            for (CrossClusterNameMapping job : allJobs) {
                LOG.info("Found document: " + job);
                jobs.add(jobOrmToJobHttp(job));

            }

            RedisTest testItem = new RedisTest("fake_id", "fake_name");
            redisTestRepository.save(testItem);



        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            LOG.info("MongoDB dbs name: " + simpleMongoConfig.mongoTemplate().getCollectionNames());
//
//            List<CrossClusterNameMapping> allJobs = simpleMongoConfig.mongoTemplate().findAll(CrossClusterNameMapping.class, "crossClusterNameMapping");
//            LOG.info("Doc size: " + allJobs.size());
//            for (CrossClusterNameMapping job : allJobs) {
//                LOG.info("Found document: " + job);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return jobs;
    }
    public List<MigrationJob> getAllInprogressjobs() {
        return jobList;
    };
    public List<MigrationJob> getAllCompletedjobs() {
        return jobList;
    };
}
