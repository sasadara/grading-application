package com.sasadara.gradingapplication.interactors.usecases.question;


import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;

public class UpdateQuestionUseCase extends TransactionalCommandUseCase<UpdateQuestionRequest> {
    private QuestionGateway questionGateway;

    public UpdateQuestionUseCase(QuestionGateway questionGateway,
                                 TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
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
        question.updateReview(request.getReview());
    }
}
