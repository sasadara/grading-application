package com.sasadara.gradingapplication.ports.primary.usecase.factory;


import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AddAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.GetAllAssignmentsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.UpdateAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.assignment.GetAllAssignmentsResponse;

public interface AssignmentUseCaseFactory {
    FunctionUseCase<GetAllAssignmentsRequest, GetAllAssignmentsResponse> getAllAssignmentsUseCase();

    CommandUseCase<AddAssignmentRequest> addAssignmentUseCase();

    CommandUseCase<UpdateAssignmentRequest> updateAssignmentUseCase();
}
