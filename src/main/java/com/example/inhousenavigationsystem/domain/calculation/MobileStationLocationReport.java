package com.example.inhousenavigationsystem.domain.calculation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileStationLocationReport {
    Long mobileStationId;
    String x;
    String y;
}
