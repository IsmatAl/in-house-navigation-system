package com.example.inhousenavigationsystem.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseStation {
    private Long id;
    private String name;
    private String x;
    private String y;
    private float detectionRadiusInMeters;
}
