package com.sasadara.gradingapplication.ports.primary.usecase.exception;

public class UnexpectedUseCaseException extends UseCaseException {
    public UnexpectedUseCaseException(String message) {
        super(message);
    }

    public UnexpectedUseCaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
