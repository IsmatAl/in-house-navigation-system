package com.example.inhousenavigationsystem.domain.calculation;

import java.util.ArrayList;

public class BaseStationReportBuilder extends BaseStationReport.BaseStationReportBuilder {
    public static BaseStationReport.BaseStationReportBuilder baseStationReportBuilder() {
        return new BaseStationReport.BaseStationReportBuilder().reports(new ArrayList<>());
    }
}
