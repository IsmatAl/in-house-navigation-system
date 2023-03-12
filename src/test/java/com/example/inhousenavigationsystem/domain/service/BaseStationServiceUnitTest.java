package com.example.inhousenavigationsystem.domain.service;

import com.example.inhousenavigationsystem.domain.calculation.BaseStationReport;
import com.example.inhousenavigationsystem.domain.calculation.DistanceCalculation;
import com.example.inhousenavigationsystem.domain.validation.BaseStationValidator;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.example.inhousenavigationsystem.domain.calculation.DistanceCalculationBuilder.distanceCalculation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BaseStationServiceUnitTest {

    @InjectMocks
    private BaseStationService baseStationService;

    @Mock
    private BaseStationValidator baseStationValidator;

    @Mock
    private MobileStationRepo mobileStationRepo;

    @Test
    void shouldValidateAndGetReports() {
        // given
        final Long baseStationId = 1L;
        final DistanceCalculation distanceCalculation1 = distanceCalculation().distance(350).build();
        final DistanceCalculation distanceCalculation2 = distanceCalculation().distance(600).build();
        final List<DistanceCalculation> reports = new ArrayList<>();
        reports.add(distanceCalculation1);
        reports.add(distanceCalculation2);
        given(mobileStationRepo.findMobileStationLocationsWithinRadius(baseStationId)).willReturn(reports);

        // when
        final BaseStationReport report = baseStationService.getReport(baseStationId);

        // then
        verify(baseStationValidator).validateBaseStationExists(baseStationId);
        verify(baseStationValidator).validateMobileStationsAreActive(reports);
        verify(baseStationValidator).validateMobileStationsAreInDetectionRange(baseStationId, reports);
        assertThat(report.getReports().get(0).getDistance()).isEqualTo(350);
        assertThat(report.getReports().get(1).getDistance()).isEqualTo(600);
        assertThat(report.getId()).isEqualTo(baseStationId);
    }
}
