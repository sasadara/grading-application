package com.sasadara.gradingapplication.interactors.usecases.question;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.QuestionRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;

public class UpdateQuestionUseCase extends TransactionalCommandUseCase<UpdateQuestionRequest> {
    private QuestionGateway questionGateway;
    private AssignmentGateway assignmentGateway;

    public UpdateQuestionUseCase(QuestionGateway questionGateway,
                                 AssignmentGateway assignmentGateway,
                                 TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.assignmentGateway = assignmentGateway;
        this.questionGateway = questionGateway;
    }

    @Override
    protected void executeInTransaction(UpdateQuestionRequest request) {

        Question question;
        if (questionGateway.findById(request.getId()).isPresent()) {
            question = questionGateway.findById(request.getId()).get();
        } else {
            throw new EntityNotFoundException(String.format("Question No : %s is not exist", request.getId()));
        }
        if (request.getQuestion() != null) {
            QuestionRequest questionRequest = request.getQuestion();
            if (questionRequest.getName() != null) {
                question.updateName(questionRequest.getName());
            }
            if (questionRequest.getNumberOfAttempts() != null) {
                question.updateNumberOfAttempts(questionRequest.getNumberOfAttempts());
            }
            if (questionRequest.getTimeSpentMints() != null) {
                question.updateTimeSpentMints(questionRequest.getTimeSpentMints());
            }

            if (questionRequest.getAssignmentId() != null) {
                if (!assignmentGateway.findById(request.getQuestion().getAssignmentId()).isPresent()) {
                    throw new EntityNotFoundException(String.format("Assignment No : %s is not exist", request.getQuestion().getAssignmentId()));
                }
                Assignment assignment = assignmentGateway.findById(request.getQuestion().getAssignmentId()).get();
                question.updateAssignment(assignment);
            }
        }
    }
}
