package com.example.demo.engine;

import com.example.demo.aspects.limiter.RateLimitIt;
import com.example.demo.query.MigrationRequest;
import com.example.demo.query.MigrationJobStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Callable;

@Component
public class QueryEngineImpl implements QueryEngine {
    @Autowired
    private RedisTemplate esClusterTemplate ;
    public Callable<ResponseEntity<?>> submitRequest(MigrationRequest request) {
//        String jobId = request.getCustomerId();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//        request.setCustomerId(jobId);
        request.setReceivedTime(timeStamp);
        Callable<ResponseEntity<?>> task = new SubmitRequestTask(request,esClusterTemplate);

        return task;
    }

    public Callable<ResponseEntity<?>> checkJobStatus(String jobId) {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Callable<ResponseEntity<?>> task = new CheckJobStatusTask(jobId);

        return task;
    }
}
