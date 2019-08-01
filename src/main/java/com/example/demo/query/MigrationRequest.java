package com.example.demo.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MigrationRequest {
    private String customerId;
    private String sourceCluster;
    private String targetCluster;
}
