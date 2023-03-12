package com.example.inhousenavigationsystem.rest.resource;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class BaseStationReportResponseResource {
    Long id;
    List<MobileStationDistanceResponseResource> reports;
}
