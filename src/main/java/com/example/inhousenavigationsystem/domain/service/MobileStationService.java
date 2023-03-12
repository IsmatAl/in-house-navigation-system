package com.example.inhousenavigationsystem.domain.service;

import com.example.inhousenavigationsystem.domain.calculation.MobileStationLocationReport;
import com.example.inhousenavigationsystem.domain.validation.MobileStationValidator;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MobileStationService {

    private final MobileStationRepo mobileStationRepo;
    private final MobileStationValidator mobileStationValidator;

    @Transactional(readOnly = true)
    public MobileStationLocationReport findLocationById(final Long id) {
        mobileStationValidator.validateMobileStationExistsById(id);
        final MobileStationLocationReport locationReport = mobileStationRepo.findLatestMobileStationLocationsById(id);
        mobileStationValidator.validateLocationExists(locationReport);
        return locationReport;
    }
}
