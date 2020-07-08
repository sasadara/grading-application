package com.sasadara.gradingapplication.interactors.usecases.assignment;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalFunctionUseCase;
import com.sasadara.gradingapplication.interactors.usecases.question.GetAllQuestionsUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.GetAllAssignmentsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.assignment.AssignmentForResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.assignment.GetAllAssignmentsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.question.QuestionForResponse;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllAssignmentsUseCase extends TransactionalFunctionUseCase<GetAllAssignmentsRequest, GetAllAssignmentsResponse> {
    private AssignmentGateway assignmentGateway;

    public GetAllAssignmentsUseCase(AssignmentGateway assignmentGateway, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.assignmentGateway = assignmentGateway;
    }

    @Override
    protected GetAllAssignmentsResponse executeInTransaction(GetAllAssignmentsRequest request) {

        GetAllAssignmentsResponse assignmentsResponse = new GetAllAssignmentsResponse();
        List<AssignmentForResponse> assignmentForResponsesList = new LinkedList<>();
        if (request.getAssignmentId() != null) {
            if (!assignmentGateway.findById(request.getAssignmentId()).isPresent()) {
                throw new EntityNotFoundException(String.format("Assignment Id : %s is not exist", request.getAssignmentId()));
            }
            Assignment assignment = assignmentGateway.findById(request.getAssignmentId()).get();
            assignmentForResponsesList.add(convertAssignment(assignment));
            assignmentsResponse.setAssignments(assignmentForResponsesList);
        } else {
            List<Assignment> assignments = assignmentGateway.findAll();
            assignmentForResponsesList = assignments.stream().map(GetAllAssignmentsUseCase::convertAssignment).collect(Collectors.toList());
            assignmentsResponse.setAssignments(assignmentForResponsesList);
        }
        return assignmentsResponse;
    }

    public static AssignmentForResponse convertAssignment(Assignment assignment) {
        AssignmentForResponse assignmentForResponse = new AssignmentForResponse();
        List<QuestionForResponse> questionForResponse = assignment.getQuestions().stream()
                .map(GetAllQuestionsUseCase::convertQuestion).collect(Collectors.toList());
        assignmentForResponse.setQuestions(questionForResponse);
        return assignmentForResponse;
    }


}
