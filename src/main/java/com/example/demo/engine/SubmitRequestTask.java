package com.example.demo.engine;

import com.example.demo.query.MigrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import com.example.demo.model.MigrationJobStatus;


public class SubmitRequestTask implements Callable<ResponseEntity<?>> {
    private static final Logger LOG = LoggerFactory.getLogger(SubmitRequestTask.class);

    private MigrationRequest request;
    private RedisTemplate redisTemplate;

    public SubmitRequestTask(MigrationRequest request, RedisTemplate redisTemplate) {
        this.request = request;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ResponseEntity<?> call() throws Exception {
        LOG.info("Sending request to message queue...");
        String customerId = request.getCustomerId();
        String sourceCluster = request.getSourceCluster();
        String targetCluster = request.getTargetCluster();
        //String host = request.getHost();
        QueryResult result = new QueryResult();
        result.setJobId(customerId);
        result.setJobStatus("SUBMITTED"); // TODO use ENUM
        result.setJobReceivedTime(request.getReceivedTime());
        result.setCustomerId(customerId);
        result.setSourceCluster(sourceCluster);
        result.setTargetCluster(targetCluster);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin","*");
        Object[] params = new Object[]{customerId, sourceCluster, targetCluster};
        String value = MessageFormat.format("customer_id:{0}~source_cluster:{1}~target_cluster:{2}~status:submitted", params);
        String key = InetAddress.getLocalHost().getHostName() + ":migration-requests";
        try {
            boolean redisadd = redisTemplate.opsForZSet().add(key, value, 1);
            LOG.info("Zadd succeed:{}",redisadd);
            return ResponseEntity
                    .ok()
                    .headers(responseHeaders)
                    .body(result);
        } catch (Exception e) {
            return ResponseEntity
                    .status(503)
                    .headers(responseHeaders)
                    .body(e.getMessage());
        }
    }
}
