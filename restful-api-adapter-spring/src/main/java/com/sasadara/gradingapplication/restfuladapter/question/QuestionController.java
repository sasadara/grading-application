package com.sasadara.gradingapplication.restfuladapter.question;

import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.QuestionUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.*;
import com.sasadara.gradingapplication.ports.primary.usecase.response.question.GetAllQuestionsResponse;
import com.sasadara.gradingapplication.restfuladapter.response.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    private static final Logger LOGGER = getLogger(QuestionController.class);
    private QuestionUseCaseFactory questionUseCaseFactory;

    @Autowired
    public QuestionController(QuestionUseCaseFactory questionUseCaseFactory) {
        this.questionUseCaseFactory = questionUseCaseFactory;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<GetAllQuestionsResponse>> getAllQuestions(@Valid @RequestParam(required = false) Long questionId) {

        FunctionUseCase<GetAllQuestionsRequest, GetAllQuestionsResponse> useCase
                = questionUseCaseFactory.getAllQuestionsUseCase();

        GetAllQuestionsRequest request = new GetAllQuestionsRequest(questionId);
        GetAllQuestionsResponse response = useCase.execute(request);

        return new ResponseEntity<>(new ResponseWrapper<>(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addQuestion(@Valid @RequestBody AddQuestionRequest addQuestionRequest) {
        LOGGER.info("Question adding => Question ID: {}",
                addQuestionRequest.getId());
        CommandUseCase<AddQuestionRequest> useCase = questionUseCaseFactory.addQuestionUseCase();

        useCase.execute(addQuestionRequest);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully added Question"),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> updateQuestion(@PathVariable(name = "id") Long questionId,
                                                                  @Valid @RequestBody QuestionRequest questionRequest) {
        LOGGER.info("Question updating => Question ID: {}",
                questionId);
        CommandUseCase<UpdateQuestionRequest> useCase = questionUseCaseFactory.updateQuestionUseCase();
        UpdateQuestionRequest request = new UpdateQuestionRequest();
        request.setId(questionId);
        request.setQuestion(questionRequest);
        useCase.execute(request);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully Update Question"),
                HttpStatus.OK);
    }

    @PatchMapping("/{id}/review")
    public ResponseEntity<ResponseWrapper<String>> updateQuestionReview(@PathVariable(name = "id") Long questionId,
                                                                        @Valid @RequestBody QuestionReviewRequest questionReviewRequest) {
        LOGGER.info("Question updating => Question ID: {}",
                questionId);
        CommandUseCase<UpdateQuestionReviewRequest> useCase = questionUseCaseFactory.updateQuestionReviewUseCase();
        UpdateQuestionReviewRequest request = new UpdateQuestionReviewRequest();
        request.setId(questionId);
        request.setReviewRequest(questionReviewRequest);
        useCase.execute(request);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully Update Question"),
                HttpStatus.OK);
    }

}
