package com.sasadara.gradingapplication.ports.primary.usecase.exception;

public class EntityAlreadyExistsException extends UseCaseException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
