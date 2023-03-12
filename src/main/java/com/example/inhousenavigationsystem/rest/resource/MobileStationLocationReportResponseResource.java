package com.example.inhousenavigationsystem.rest.resource;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MobileStationLocationReportResponseResource {
    Long mobileStationId;
    String x;
    String y;
}
