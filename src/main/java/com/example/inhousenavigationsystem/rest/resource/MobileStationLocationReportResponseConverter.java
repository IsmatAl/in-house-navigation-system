package com.example.inhousenavigationsystem.rest.resource;

import com.example.inhousenavigationsystem.domain.calculation.MobileStationLocationReport;
import org.springframework.stereotype.Component;

@Component
public class MobileStationLocationReportResponseConverter {
    public MobileStationLocationReportResponseResource convertToResource(final MobileStationLocationReport locationReport) {
        return MobileStationLocationReportResponseResource.builder().mobileStationId(locationReport.getMobileStationId())
                .x(locationReport.getX())
                .y(locationReport.getY())
                .build();
    }
}
