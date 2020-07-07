package com.sasadara.gradingapplication.restfuladapter.question;

import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.QuestionUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.AddQuestionRequest;
import com.sasadara.gradingapplication.restfuladapter.response.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addQuestion(@Valid @RequestBody AddQuestionRequest addQuestionRequest) {
        LOGGER.info("Question adding => Question ID: {}",
                addQuestionRequest.getId());
        CommandUseCase<AddQuestionRequest> useCase = questionUseCaseFactory.addQuestionUseCase();

        useCase.execute(addQuestionRequest);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully added Question"),
                HttpStatus.CREATED);
    }

}
