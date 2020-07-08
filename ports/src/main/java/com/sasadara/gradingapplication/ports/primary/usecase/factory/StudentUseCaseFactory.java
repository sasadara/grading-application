package com.sasadara.gradingapplication.ports.primary.usecase.factory;


import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.AddStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.GetAllStudentsDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.UpdateStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.student.GetAllStudentsDetailsResponse;

public interface StudentUseCaseFactory {
    FunctionUseCase<GetAllStudentsDetailsRequest, GetAllStudentsDetailsResponse> getStudentDetailsUseCase();

    CommandUseCase<AddStudentDetailsRequest> addStudentDetailsUseCase();

    CommandUseCase<UpdateStudentDetailsRequest> updateStudentDetailsUseCase();
}
