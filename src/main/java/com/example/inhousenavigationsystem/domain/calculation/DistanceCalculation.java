package com.example.inhousenavigationsystem.domain.calculation;

import com.example.inhousenavigationsystem.domain.model.MobileStationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class DistanceCalculation {
    Long mobileStationId;
    double distance;
    Instant timestamp;
    MobileStationStatus status;
}
