package com.example.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MigrationJobDto {
    private String customerId;
    private String sourceCluster;
    private String targetCluster;
    private String jobStatus;
    private int indexCountOnSourceCluster;
    private int indexCountOnTargetCluster;
}
