package com.example.inhousenavigationsystem.rest.resource;

import java.util.ArrayList;

public class BaseStationReportResponseResourceBuilder extends BaseStationReportResponseResource.BaseStationReportResponseResourceBuilder{

    public static BaseStationReportResponseResource.BaseStationReportResponseResourceBuilder baseStationReportResponseResource() {
        return new BaseStationReportResponseResource.BaseStationReportResponseResourceBuilder().reports(new ArrayList<>());
    }
}
