package com.example.inhousenavigationsystem.domain.model;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class MobileStation {
    private Long id;
    private String name;
    private Set<MobileStationLocation> locations;
    private MobileStationModel model;
    private MobileStationStatus status;
}
