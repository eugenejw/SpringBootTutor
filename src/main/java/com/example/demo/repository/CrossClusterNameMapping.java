package com.example.demo.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrossClusterNameMapping {
    private String tenantId;
    private String sourceCluster;
    private String targetCluster;
    private boolean migrationRequestReceived;
    private boolean migrationPreparationFinished;
    private boolean migrationCompleted;
}
