package com.example.inhousenavigationsystem.rest;

import com.example.inhousenavigationsystem.domain.calculation.BaseStationReport;
import com.example.inhousenavigationsystem.domain.service.BaseStationService;
import com.example.inhousenavigationsystem.rest.resource.BaseStationReportRecourseConverter;
import com.example.inhousenavigationsystem.rest.resource.BaseStationReportResponseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base-stations")
@RequiredArgsConstructor
public class BaseStationController {

    private final BaseStationService baseStationService;
    private final BaseStationReportRecourseConverter baseStationReportConverter;

    @GetMapping("/{id}/reports")
    public BaseStationReportResponseResource getReports(@PathVariable final Long id) {
        final BaseStationReport baseStationReport = baseStationService.getReport(id);
        return baseStationReportConverter.convertToResource(baseStationReport);
    }
}
