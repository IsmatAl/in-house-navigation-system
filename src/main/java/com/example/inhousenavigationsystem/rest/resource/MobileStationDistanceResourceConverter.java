package com.example.inhousenavigationsystem.rest.resource;

import com.example.inhousenavigationsystem.domain.calculation.DistanceCalculation;
import org.springframework.stereotype.Component;

@Component
public class MobileStationDistanceResourceConverter {

    public MobileStationDistanceResponseResource convertToResource(final DistanceCalculation distanceCalculation) {
        return MobileStationDistanceResponseResource.builder().id(distanceCalculation.getMobileStationId())
                .distance((float) distanceCalculation.getDistance())
                .timestamp(distanceCalculation.getTimestamp())
                .build();
    }
}
