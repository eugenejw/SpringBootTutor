package com.example.demo.engine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryResult {
    private String jobId;
    private String customerId;
    private String sourceCluster;
    private String targetCluster;
    private String jobStatus;
    private String jobReceivedTime;
}
