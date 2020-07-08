package com.sasadara.gradingapplication.interactors.usecases.question;


import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalFunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.GetAllQuestionsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.question.GetAllQuestionsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.question.QuestionForResponse;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllQuestionsUseCase extends TransactionalFunctionUseCase<GetAllQuestionsRequest, GetAllQuestionsResponse> {
    private QuestionGateway questionGateway;

    public GetAllQuestionsUseCase(QuestionGateway questionGateway, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.questionGateway = questionGateway;
    }

    @Override
    protected GetAllQuestionsResponse executeInTransaction(GetAllQuestionsRequest request) {

        GetAllQuestionsResponse getAllQuestionsResponse = new GetAllQuestionsResponse();
        List<QuestionForResponse> questionForResponsesList = new LinkedList<>();
        if (request.getQuestionId() != null) {
            if (!questionGateway.findById(request.getQuestionId()).isPresent()) {
                throw new EntityNotFoundException(String.format("Question Id : %s is not exist", request.getQuestionId()));
            }
            Question question = questionGateway.findById(request.getQuestionId()).get();
            questionForResponsesList.add(convertQuestion(question));
            getAllQuestionsResponse.setQuestions(questionForResponsesList);
        } else {
            List<Question> questions = questionGateway.findAll();
            questionForResponsesList = questions.stream().map(GetAllQuestionsUseCase::convertQuestion).collect(Collectors.toList());
            getAllQuestionsResponse.setQuestions(questionForResponsesList);
        }
        return getAllQuestionsResponse;
    }

    public static QuestionForResponse convertQuestion(Question question) {
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
