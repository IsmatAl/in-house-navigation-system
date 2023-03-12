package com.example.inhousenavigationsystem.domain.validation;

import com.example.inhousenavigationsystem.domain.calculation.MobileStationLocationReport;
import com.example.inhousenavigationsystem.domain.exception.InvalidMobileStationException;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MobileStationValidator {

    private final MobileStationRepo mobileStationRepo;

    private boolean existsById(final Long id) {
        return mobileStationRepo.existsById(id);
    }

    @Transactional(readOnly = true)
    public void validateMobileStationExistsById(final Long id) {
        if (!existsById(id)) {
            throw new InvalidMobileStationException("Mobile station with given id does not exist", "invalid-mobile-station-id", null);
        }
    }

    public void validateLocationExists(final MobileStationLocationReport locationReport) {
        if (Objects.isNull(locationReport)) {
            throw new InvalidMobileStationException("No location found mobile station with given id", "no-location", null);
        }
    }
}
