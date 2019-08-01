package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MigrationJob {
    private String customerId;
    private String sourceCluster;
    private String targetCluster;
    private String jobStatus;
    private int indexCountOnSourceCluster;
    private int indexCountOnTargetCluster;
}
