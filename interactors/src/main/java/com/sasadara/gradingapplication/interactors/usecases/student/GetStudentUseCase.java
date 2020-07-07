package com.sasadara.gradingapplication.interactors.usecases.student;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalFunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.GetStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.assignment.AssignmentForResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.question.QuestionForResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.student.GetStudentDetailsResponse;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;

import java.util.List;
import java.util.stream.Collectors;

public class GetStudentUseCase extends TransactionalFunctionUseCase<GetStudentDetailsRequest, GetStudentDetailsResponse> {
    private StudentGateway studentGateway;

    public GetStudentUseCase(StudentGateway studentGateway, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.studentGateway = studentGateway;
    }

    @Override
    protected GetStudentDetailsResponse executeInTransaction(GetStudentDetailsRequest request) {


        if (!studentGateway.findById(request.getStudentId()).isPresent()) {
            throw new EntityNotFoundException(String.format("Student Id : %s is not exist", request.getStudentId()));
        }

        Student student = studentGateway.findById(request.getStudentId()).get();

        GetStudentDetailsResponse studentDetailsResponse = new GetStudentDetailsResponse();
        List<AssignmentForResponse> assignmentForResponses = student.getAssignments().stream()
                .map(GetStudentUseCase::convertAssignment).collect(Collectors.toList());
        studentDetailsResponse.setAssignments(assignmentForResponses);
        return studentDetailsResponse;
    }

    private static AssignmentForResponse convertAssignment(Assignment assignment) {
        AssignmentForResponse assignmentForResponse = new AssignmentForResponse();
        List<QuestionForResponse> questionForResponse = assignment.getQuestions().stream()
                .map(GetStudentUseCase::convertQuestion).collect(Collectors.toList());
        assignmentForResponse.setQuestions(questionForResponse);
        return assignmentForResponse;
    }

    private static QuestionForResponse convertQuestion(Question question) {
        QuestionForResponse questionForResponse = new QuestionForResponse();
        questionForResponse.setId(question.getId());
        questionForResponse.setName(question.getName());
        questionForResponse.setNumberOfAttempts(question.getNumberOfAttempts());
        questionForResponse.setResult(question.getResult());
        questionForResponse.setTimeSpentMints(question.getTimeSpentMints());
        questionForResponse.setReview(question.getReview());
        return questionForResponse;
    }
}
