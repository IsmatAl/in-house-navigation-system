package com.example.inhousenavigationsystem.domain.exception;

import java.util.List;

public class InvalidCalculationException extends BusinessException {
    public InvalidCalculationException(final String message, final String key, final List<String> values) {
        super(message, key, values);
    }
}
