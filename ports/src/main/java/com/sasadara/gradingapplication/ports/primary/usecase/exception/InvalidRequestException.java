package com.sasadara.gradingapplication.ports.primary.usecase.exception;

public class InvalidRequestException extends UseCaseException {
    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
