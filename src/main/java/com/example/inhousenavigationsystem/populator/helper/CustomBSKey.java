package com.example.inhousenavigationsystem.populator.helper;

import com.example.inhousenavigationsystem.persistence.entity.BaseStationEntity;

public record CustomBSKey(String name) {
    public CustomBSKey(final BaseStationEntity baseStation) {
        this(baseStation.getName());
    }
}
