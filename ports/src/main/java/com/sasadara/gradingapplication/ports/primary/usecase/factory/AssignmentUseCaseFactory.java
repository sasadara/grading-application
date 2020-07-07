package com.sasadara.gradingapplication.ports.primary.usecase.factory;


import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AddAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.UpdateAssignmentRequest;

public interface AssignmentUseCaseFactory {
    CommandUseCase<AddAssignmentRequest> addAssignmentUseCase();

    CommandUseCase<UpdateAssignmentRequest> updateAssignmentUseCase();
}
