package com.example.inhousenavigationsystem.domain.service;

import com.example.inhousenavigationsystem.domain.calculation.BaseStationReport;
import com.example.inhousenavigationsystem.domain.calculation.DistanceCalculation;
import com.example.inhousenavigationsystem.domain.validation.BaseStationValidator;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseStationService {
    private final BaseStationValidator baseStationValidator;
    private final MobileStationRepo mobileStationRepo;

    @Transactional(readOnly = true)
    public BaseStationReport getReport(final Long id) {
        baseStationValidator.validateBaseStationExists(id);
        final List<DistanceCalculation> reports = mobileStationRepo.findMobileStationLocationsWithinRadius(id);
        baseStationValidator.validateMobileStationsAreInDetectionRange(id, reports);
        baseStationValidator.validateMobileStationsAreActive(reports);
        return BaseStationReport.builder().id(id).reports(reports).build();
    }
}
