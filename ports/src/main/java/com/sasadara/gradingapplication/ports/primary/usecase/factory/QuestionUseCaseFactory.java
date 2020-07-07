package com.sasadara.gradingapplication.ports.primary.usecase.factory;


import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.AddQuestionRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionRequest;

public interface QuestionUseCaseFactory {
    CommandUseCase<AddQuestionRequest> addQuestionUseCase();

    CommandUseCase<UpdateQuestionRequest> updateQuestionUseCase();
}
