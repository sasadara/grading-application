package com.sasadara.gradingapplication.ports.primary.usecase.factory;


import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.AddQuestionRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.GetAllQuestionsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionReviewRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.question.GetAllQuestionsResponse;

public interface QuestionUseCaseFactory {
    FunctionUseCase<GetAllQuestionsRequest, GetAllQuestionsResponse> getAllQuestionsUseCase();

    CommandUseCase<AddQuestionRequest> addQuestionUseCase();

    CommandUseCase<UpdateQuestionRequest> updateQuestionUseCase();

    CommandUseCase<UpdateQuestionReviewRequest> updateQuestionReviewUseCase();
}
