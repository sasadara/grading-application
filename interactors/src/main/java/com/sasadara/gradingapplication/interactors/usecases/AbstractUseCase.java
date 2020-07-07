package com.sasadara.gradingapplication.interactors.usecases;


import com.sasadara.gradingapplication.ports.primary.usecase.exception.UseCaseException;

public class AbstractUseCase {
    private boolean executed;

    protected void checkFirstExecution() {
        if (executed) {
            throw new UseCaseAlreadyExecutedException();
        }
        executed = true;
    }

    public static class UseCaseAlreadyExecutedException extends UseCaseException {
    }
}
