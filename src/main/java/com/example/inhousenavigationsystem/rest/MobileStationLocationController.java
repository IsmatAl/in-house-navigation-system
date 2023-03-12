package com.example.inhousenavigationsystem.rest;

import com.example.inhousenavigationsystem.domain.service.MobileStationService;
import com.example.inhousenavigationsystem.rest.resource.MobileStationLocationReportResponseConverter;
import com.example.inhousenavigationsystem.rest.resource.MobileStationLocationReportResponseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class MobileStationLocationController {

    private final MobileStationService mobileStationService;
    private final MobileStationLocationReportResponseConverter converter;

    @GetMapping("/{id}")
    public MobileStationLocationReportResponseResource getMobileStationCoordinates(@PathVariable("id") final Long id) {
        return converter.convertToResource(mobileStationService.findLocationById(id));
    }
}
