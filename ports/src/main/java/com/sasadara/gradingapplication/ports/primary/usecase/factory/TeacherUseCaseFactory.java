package com.sasadara.gradingapplication.ports.primary.usecase.factory;


import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.AddTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.GetTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.UpdateTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.GetTeacherDetailsResponse;

public interface TeacherUseCaseFactory {
    FunctionUseCase<GetTeacherDetailsRequest, GetTeacherDetailsResponse> getTeacherDetailsUseCase();

    CommandUseCase<UpdateTeacherDetailsRequest> updateTeacherDetailsUseCase();

    CommandUseCase<AddTeacherDetailsRequest> addTeacherDetailsUseCase();
}
