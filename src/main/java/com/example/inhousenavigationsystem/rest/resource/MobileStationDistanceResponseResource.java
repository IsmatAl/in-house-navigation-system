package com.example.inhousenavigationsystem.rest.resource;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class MobileStationDistanceResponseResource {
    Long id;
    float distance;
    Instant timestamp;
}
