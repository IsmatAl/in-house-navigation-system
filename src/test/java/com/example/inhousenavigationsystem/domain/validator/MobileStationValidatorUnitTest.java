package com.example.inhousenavigationsystem.domain.validator;

import com.example.inhousenavigationsystem.domain.calculation.MobileStationLocationReport;
import com.example.inhousenavigationsystem.domain.exception.InvalidMobileStationException;
import com.example.inhousenavigationsystem.domain.validation.MobileStationValidator;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MobileStationValidatorUnitTest {

    @InjectMocks
    private MobileStationValidator mobileStationValidator;

    @Mock
    private MobileStationRepo mobileStationRepo;

    @Test
    void shouldThrowExceptionIfMobileStationDoesNotExist() {
        // given
        final Long mobileStationId = 1L;
        given(mobileStationRepo.existsById(mobileStationId)).willReturn(false);

        // when
        final InvalidMobileStationException invalidMobileStationException = (InvalidMobileStationException) catchThrowable(
                () -> mobileStationValidator.validateMobileStationExistsById(mobileStationId));

        // then
        assertThat(invalidMobileStationException.getMessage()).isEqualTo("Mobile station with given id does not exist");
        assertThat(invalidMobileStationException.getKey()).isEqualTo("invalid-mobile-station-id");
        assertThat(invalidMobileStationException.getValues()).isNull();
    }

    @Test
    void shouldThrowExceptionWhenGettingMobileStationWithInvalidStatus() {
        // given
        final MobileStationLocationReport locationReport = null;

        // when
        final InvalidMobileStationException invalidMobileStationException = (InvalidMobileStationException) catchThrowable(
                () -> mobileStationValidator.validateLocationExists(locationReport));

        // then
        assertThat(invalidMobileStationException.getMessage())
                .isEqualTo("No location found mobile station with given id");
        assertThat(invalidMobileStationException.getKey()).isEqualTo("no-location");
        assertThat(invalidMobileStationException.getValues()).isNull();
    }
}
