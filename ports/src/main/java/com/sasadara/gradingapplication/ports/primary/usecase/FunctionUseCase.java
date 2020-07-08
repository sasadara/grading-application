package com.sasadara.gradingapplication.ports.primary.usecase;


import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import com.sasadara.gradingapplication.ports.primary.usecase.response.Response;

public interface FunctionUseCase<T extends Request, R extends Response> extends UseCase {
    R execute(T request);
}
