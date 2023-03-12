package com.example.inhousenavigationsystem.domain.validation;

import com.example.inhousenavigationsystem.domain.calculation.DistanceCalculation;
import com.example.inhousenavigationsystem.domain.exception.InvalidBaseStationException;
import com.example.inhousenavigationsystem.domain.exception.InvalidCalculationException;
import com.example.inhousenavigationsystem.domain.model.MobileStationStatus;
import com.example.inhousenavigationsystem.persistence.entity.BaseStationEntity;
import com.example.inhousenavigationsystem.persistence.entity.BaseStationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BaseStationValidator {
    private final BaseStationRepo baseStationRepo;

    @Transactional(readOnly = true)
    public void validateBaseStationExists(final Long id) {
        if (!existsById(id)) {
            throw new InvalidBaseStationException("Base station with given id does not exist", "invalid-base-station-id", null);
        }
    }


    private boolean existsById(final Long id) {
        return baseStationRepo.existsById(id);
    }

    public void validateMobileStationsAreInDetectionRange(
            final Long baseStationId,
            final List<DistanceCalculation> reports
    ) {
        final BaseStationEntity baseStationEntity = baseStationRepo.getReferenceById(baseStationId);
        reports.forEach(report -> {
            if (report.getDistance() > baseStationEntity.getDetectionRadiusInMeters()) {
                throw new InvalidCalculationException("Mobile station is not in the detection radius",
                        "mobile-station-outside-range", null);
            }
        });
    }

    public void validateMobileStationsAreActive(final List<DistanceCalculation> reports) {
        reports.forEach(report -> {
            if (report.getStatus().compareTo(MobileStationStatus.ACTIVE) != 0) {
                throw new InvalidCalculationException("Mobile station is not in active status",
                        "invalid-mobile-station-status", null);
            }
        });
    }
}
