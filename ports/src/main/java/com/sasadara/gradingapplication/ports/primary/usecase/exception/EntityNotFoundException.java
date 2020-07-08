package com.sasadara.gradingapplication.ports.primary.usecase.exception;

public class EntityNotFoundException extends UseCaseException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
