package com.sasadara.gradingapplication.ports.primary.usecase;


import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;

public interface CommandUseCase<T extends Request> extends UseCase {
    void execute(T request);
}
