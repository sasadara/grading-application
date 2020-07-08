package com.sasadara.gradingapplication.interactors.usecases.question;


import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionReviewRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;

public class UpdateQuestionReviewUseCase extends TransactionalCommandUseCase<UpdateQuestionReviewRequest> {
    private QuestionGateway questionGateway;

    public UpdateQuestionReviewUseCase(QuestionGateway questionGateway,
                                       TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.questionGateway = questionGateway;
    }

    @Override
    protected void executeInTransaction(UpdateQuestionReviewRequest request) {

        Question question;
        if (questionGateway.findById(request.getId()).isPresent()) {
            question = questionGateway.findById(request.getId()).get();
        } else {
            throw new EntityNotFoundException(String.format("Question No : %s is not exist", request.getId()));
        }
        if (request.getReviewRequest() != null) {
            question.updateReview(request.getReviewRequest().getReview());
        }
    }
}
