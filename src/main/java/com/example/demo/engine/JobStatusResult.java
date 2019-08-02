package com.example.demo.engine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobStatusResult {
    private String jobStatus;
    private String customerId;
    private int indexCountOnSourceCluster;
    private int indexCountOnTargetCluster;
}
