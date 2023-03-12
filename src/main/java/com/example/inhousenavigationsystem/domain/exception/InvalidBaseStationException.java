package com.example.inhousenavigationsystem.domain.exception;

import java.util.List;

public class InvalidBaseStationException extends BusinessException {

    public InvalidBaseStationException(final String message, final String key, final List<String> values) {
        super(message, key, values);
    }
}