package com.example.inhousenavigationsystem.rest.resource;

import com.example.inhousenavigationsystem.domain.calculation.BaseStationReport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BaseStationReportRecourseConverter {

    private final MobileStationDistanceResourceConverter mobileStationDistanceConverter;

    public BaseStationReportResponseResource convertToResource(final BaseStationReport baseStationReport) {
        return BaseStationReportResponseResource.builder().id(baseStationReport.getId())
                .reports(baseStationReport.getReports().stream().map(mobileStationDistanceConverter::convertToResource).toList())
                .build();
    }
}
