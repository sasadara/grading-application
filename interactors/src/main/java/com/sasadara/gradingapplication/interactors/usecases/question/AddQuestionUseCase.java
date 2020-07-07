package com.sasadara.gradingapplication.interactors.usecases.question;


import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.secondary.datastore.EntityFactory;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityAlreadyExistsException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.AddQuestionRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;

public class AddQuestionUseCase extends TransactionalCommandUseCase<AddQuestionRequest> {
    private QuestionGateway questionGateway;
    private EntityFactory entityFactory;

    public AddQuestionUseCase(QuestionGateway questionGateway, EntityFactory entityFactory,
                              TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.questionGateway = questionGateway;
        this.entityFactory = entityFactory;
    }

    @Override
    protected void executeInTransaction(AddQuestionRequest request) {
        long requestId = request.getId();
        Question question = entityFactory.question();

        question.updateId(request.getId());
        question.updateName(request.getName());
        question.updateResult(request.getResult());
        question.updateNumberOfAttempts(request.getNumberOfAttempts());
        question.updateTimeSpentMints(request.getTimeSpentMints());
        question.updateReview(request.getReview());

        if (!questionGateway.findById(requestId).isPresent()) {
            questionGateway.addNew(question);
        } else {
            throw new EntityAlreadyExistsException(String.format("Question with Question Id: %s is already exist", requestId));
        }
    }
}
