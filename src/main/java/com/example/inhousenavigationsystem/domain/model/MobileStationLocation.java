package com.example.inhousenavigationsystem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileStationLocation {
    private Long id;
    private String x;
    private String y;
}
