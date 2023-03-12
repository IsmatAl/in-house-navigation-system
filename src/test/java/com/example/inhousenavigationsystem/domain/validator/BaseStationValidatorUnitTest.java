package com.example.inhousenavigationsystem.domain.validator;

import com.example.inhousenavigationsystem.domain.calculation.DistanceCalculation;
import com.example.inhousenavigationsystem.domain.exception.InvalidBaseStationException;
import com.example.inhousenavigationsystem.domain.exception.InvalidCalculationException;
import com.example.inhousenavigationsystem.domain.model.MobileStationStatus;
import com.example.inhousenavigationsystem.domain.validation.BaseStationValidator;
import com.example.inhousenavigationsystem.persistence.entity.BaseStationEntity;
import com.example.inhousenavigationsystem.persistence.entity.BaseStationRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.example.inhousenavigationsystem.domain.calculation.DistanceCalculationBuilder.distanceCalculation;
import static com.example.inhousenavigationsystem.persistence.entity.BaseStationEntityBuilder.baseStationEntity;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BaseStationValidatorUnitTest {

    @InjectMocks
    private BaseStationValidator baseStationValidator;

    @Mock
    private BaseStationRepo baseStationRepo;

    @Test
    void shouldThrowExceptionIfBaseStationDoesNotExist() {
        // given
        final Long baseStationId = 1L;
        given(baseStationRepo.existsById(baseStationId)).willReturn(false);

        // when
        final InvalidBaseStationException invalidBaseStationException = (InvalidBaseStationException) catchThrowable(
                () -> baseStationValidator.validateBaseStationExists(baseStationId));

        // then
        assertThat(invalidBaseStationException.getMessage()).isEqualTo("Base station with given id does not exist");
        assertThat(invalidBaseStationException.getKey()).isEqualTo("invalid-base-station-id");
        assertThat(invalidBaseStationException.getValues()).isNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"INACTIVE"})
    void shouldThrowExceptionWhenGettingMobileStationWithInvalidStatus(String mobileStationStatus) {
        // given
        final DistanceCalculation activeMobileStation = distanceCalculation().status(MobileStationStatus.valueOf(mobileStationStatus))
                .build();
        final List<DistanceCalculation> reports = new ArrayList<>();
        reports.add(activeMobileStation);

        // when
        final InvalidCalculationException invalidCalculationException = (InvalidCalculationException) catchThrowable(
                () -> baseStationValidator.validateMobileStationsAreActive(reports));

        // then
        assertThat(invalidCalculationException.getMessage())
                .isEqualTo("Mobile station is not in active status");
        assertThat(invalidCalculationException.getKey()).isEqualTo("invalid-mobile-station-status");
        assertThat(invalidCalculationException.getValues()).isNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ACTIVE"})
    void shouldNotThrowExceptionWhenGettingMobileStationWithValidStatus(String mobileStationStatus) {
        // given
        final DistanceCalculation activeMobileStation = distanceCalculation().status(MobileStationStatus.valueOf(mobileStationStatus))
                .build();
        final List<DistanceCalculation> reports = new ArrayList<>();
        reports.add(activeMobileStation);

        // then
        assertThatNoException().isThrownBy(() -> baseStationValidator.validateMobileStationsAreActive(reports));
    }

    @Test
    void shouldThrowExceptionIfDistanceFromMobileStationToBaseStationIsGreaterThanBaseStationDetectionRadius() {
        // given
        final Long baseStationId = 1L;
        final DistanceCalculation distanceCalculation = distanceCalculation().distance(750).build();
        final List<DistanceCalculation> reports = new ArrayList<>();
        reports.add(distanceCalculation);

        final BaseStationEntity baseStation = baseStationEntity().detectionRadiusInMeters(600).build();

        given(baseStationRepo.getReferenceById(baseStationId)).willReturn(baseStation);

        // when
        final InvalidCalculationException invalidCalculationException = (InvalidCalculationException) catchThrowable(
                () -> baseStationValidator.validateMobileStationsAreInDetectionRange(baseStationId, reports));

        // then
        assertThat(invalidCalculationException.getMessage())
                .isEqualTo("Mobile station is not in the detection radius");
        assertThat(invalidCalculationException.getKey()).isEqualTo("mobile-station-outside-range");
        assertThat(invalidCalculationException.getValues()).isNull();
    }

    @Test
    void shouldNotThrowExceptionIfDistanceFromMobileStationToBaseStationIsLessThanOrEqualToBaseStationDetectionRadius() {
        // given
        final Long baseStationId = 1L;
        final DistanceCalculation distanceCalculation1 = distanceCalculation().distance(350).build();
        final DistanceCalculation distanceCalculation2 = distanceCalculation().distance(600).build();
        final List<DistanceCalculation> reports = new ArrayList<>();
        reports.add(distanceCalculation1);
        reports.add(distanceCalculation2);

        final BaseStationEntity baseStation = baseStationEntity().detectionRadiusInMeters(600).build();

        given(baseStationRepo.getReferenceById(baseStationId)).willReturn(baseStation);

        // then
        assertThatNoException().isThrownBy(() -> baseStationValidator.validateMobileStationsAreInDetectionRange(baseStationId, reports));
    }

}
