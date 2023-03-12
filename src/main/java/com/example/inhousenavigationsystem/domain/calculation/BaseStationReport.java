package com.example.inhousenavigationsystem.domain.calculation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BaseStationReport {
    Long id;
    List<DistanceCalculation> reports;
}
