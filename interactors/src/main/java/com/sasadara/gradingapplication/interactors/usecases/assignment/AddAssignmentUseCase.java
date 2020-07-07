package com.sasadara.gradingapplication.interactors.usecases.assignment;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.EntityFactory;
import com.sasadara.gradingapplication.ports.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AddAssignmentRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;

import java.util.LinkedList;
import java.util.List;

public class AddAssignmentUseCase extends TransactionalCommandUseCase<AddAssignmentRequest> {
    private AssignmentGateway assignmentGateway;
    private QuestionGateway questionGateway;
    private EntityFactory entityFactory;

    public AddAssignmentUseCase(AssignmentGateway assignmentGateway, QuestionGateway questionGateway,
                                EntityFactory entityFactory, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.assignmentGateway = assignmentGateway;
        this.questionGateway = questionGateway;
        this.entityFactory = entityFactory;
    }

    @Override
    protected void executeInTransaction(AddAssignmentRequest request) {

        Assignment assignment = entityFactory.assignment();
        assignment.updateName(request.getName());
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
