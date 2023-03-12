package com.example.inhousenavigationsystem.populator.helper;

import com.example.inhousenavigationsystem.persistence.entity.MobileStationEntity;

public record CustomMSKey(String name) {
	public CustomMSKey(final MobileStationEntity mobileStation) { this(mobileStation.getName());
	}
}