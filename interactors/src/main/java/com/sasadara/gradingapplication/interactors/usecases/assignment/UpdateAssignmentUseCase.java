package com.sasadara.gradingapplication.interactors.usecases.assignment;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.UpdateAssignmentRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;

import java.util.LinkedList;
import java.util.List;

public class UpdateAssignmentUseCase extends TransactionalCommandUseCase<UpdateAssignmentRequest> {
    private AssignmentGateway assignmentGateway;
    private QuestionGateway questionGateway;

    public UpdateAssignmentUseCase(AssignmentGateway assignmentGateway, QuestionGateway questionGateway,
                                   TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.assignmentGateway = assignmentGateway;
        this.questionGateway = questionGateway;
    }

    @Override
    protected void executeInTransaction(UpdateAssignmentRequest request) {
        Assignment assignment;
        if (assignmentGateway.findById(request.getId()).isPresent()) {
            assignment = assignmentGateway.findById(request.getId()).get();
        } else {
            throw new EntityNotFoundException(String.format("Assignment No : %s is not exist", request.getId()));
        }
        List<Question> questions = new LinkedList<>();
        for (Long id : request.getQuestions()) {
            if (questionGateway.findById(id).isPresent()) {
                questions.add(questionGateway.findById(id).get());
            }
        }
        assignment.updateQuestions(questions);
        assignmentGateway.addNew(assignment);

    }
}
