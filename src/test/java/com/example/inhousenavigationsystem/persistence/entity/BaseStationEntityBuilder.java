package com.example.inhousenavigationsystem.persistence.entity;

public class BaseStationEntityBuilder extends BaseStationEntity.BaseStationEntityBuilder<BaseStationEntity, BaseStationEntityBuilder> {

    public static BaseStationEntityBuilder baseStationEntity() {
        return new BaseStationEntityBuilder();
    }

    @Override
    protected BaseStationEntityBuilder self() {
        return this;
    }

    @Override
    public BaseStationEntity build() {
        return new BaseStationEntity(this);
    }
}
