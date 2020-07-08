package com.sasadara.gradingapplication.ports.primary.usecase.factory;


import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.AddTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.GetAllTeachersDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.UpdateTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.GetAllTeachersDetailsResponse;

public interface TeacherUseCaseFactory {
    FunctionUseCase<GetAllTeachersDetailsRequest, GetAllTeachersDetailsResponse> getTeacherDetailsUseCase();

    CommandUseCase<UpdateTeacherDetailsRequest> updateTeacherDetailsUseCase();

    CommandUseCase<AddTeacherDetailsRequest> addTeacherDetailsUseCase();
}
