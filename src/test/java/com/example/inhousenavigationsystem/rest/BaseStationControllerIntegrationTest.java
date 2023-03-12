package com.example.inhousenavigationsystem.rest;

import com.example.inhousenavigationsystem.domain.calculation.BaseStationReport;
import com.example.inhousenavigationsystem.domain.calculation.DistanceCalculation;
import com.example.inhousenavigationsystem.domain.service.BaseStationService;
import com.example.inhousenavigationsystem.rest.resource.BaseStationReportRecourseConverter;
import com.example.inhousenavigationsystem.rest.resource.BaseStationReportResponseResource;
import com.example.inhousenavigationsystem.rest.resource.MobileStationDistanceResourceConverter;
import com.example.inhousenavigationsystem.rest.resource.MobileStationDistanceResponseResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.inhousenavigationsystem.domain.calculation.BaseStationReportBuilder.baseStationReportBuilder;
import static com.example.inhousenavigationsystem.domain.calculation.DistanceCalculationBuilder.distanceCalculation;
import static com.example.inhousenavigationsystem.rest.resource.BaseStationReportResponseResourceBuilder.baseStationReportResponseResource;
import static com.example.inhousenavigationsystem.rest.resource.MobileStationDistanceResponseResourceBuilder.mobileStationDistanceResponseResource;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;


@WebMvcTest(controllers = BaseStationController.class)
public class BaseStationControllerIntegrationTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BaseStationService baseStationService;

    @MockBean
    private BaseStationReportRecourseConverter baseStationReportRecourseConverter;

    @MockBean
    private MobileStationDistanceResourceConverter mobileStationDistanceResourceConverter;

    @Test
    void shouldGetReports() throws Exception {
        // given
        final BaseStationReport  baseStationReport = baseStationReportBuilder().id(1L).build();
        final BaseStationReportResponseResource baseStationReportResponseResource = baseStationReportResponseResource().id(1L).build();

        final DistanceCalculation distanceCalculation1 = distanceCalculation().distance(350.0f).build();
        final MobileStationDistanceResponseResource mobileStationDistanceResponseResource1 = mobileStationDistanceResponseResource().distance(350.0f).build();
        final DistanceCalculation distanceCalculation2 = distanceCalculation().distance(600.0f).build();
        final MobileStationDistanceResponseResource mobileStationDistanceResponseResource2 = mobileStationDistanceResponseResource().distance(600.0f).build();

        baseStationReport.getReports().add(distanceCalculation1);
        baseStationReport.getReports().add(distanceCalculation2);

        baseStationReportResponseResource.getReports().add(mobileStationDistanceResponseResource1);
        baseStationReportResponseResource.getReports().add(mobileStationDistanceResponseResource2);

        given(baseStationReportRecourseConverter.convertToResource(baseStationReport)).willReturn(baseStationReportResponseResource);
        given(mobileStationDistanceResourceConverter.convertToResource(distanceCalculation1)).willReturn(mobileStationDistanceResponseResource1);
        given(mobileStationDistanceResourceConverter.convertToResource(distanceCalculation2)).willReturn(mobileStationDistanceResponseResource2);
        given(baseStationService.getReport(1L)).willReturn(baseStationReport);

        // when
        final ResultActions perform = mockMvc.perform(
                get("/base-stations/"+ 1L + "/reports").accept(APPLICATION_JSON));

        // then
        perform.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.reports[0].distance", is(350.0)))
                .andExpect(jsonPath("$.reports[1].distance", is(600.0)));
    }
}
