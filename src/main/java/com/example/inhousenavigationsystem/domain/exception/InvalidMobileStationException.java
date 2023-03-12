package com.example.inhousenavigationsystem.domain.exception;

import java.util.List;

public class InvalidMobileStationException extends BusinessException {

    public InvalidMobileStationException(final String message, final String key, final List<String> values) {
        super(message, key, values);
    }
}