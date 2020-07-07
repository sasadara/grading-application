package com.sasadara.gradingapplication.restfuladapter.assignment;

import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.AssignmentUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AddAssignmentRequest;
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
@RequestMapping(path = "/assignment")
public class AssignmentController {

    private static final Logger LOGGER = getLogger(AssignmentController.class);
    private AssignmentUseCaseFactory assignmentUseCaseFactory;

    @Autowired
    public AssignmentController(AssignmentUseCaseFactory assignmentUseCaseFactory) {
        this.assignmentUseCaseFactory = assignmentUseCaseFactory;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addAssignment(@Valid @RequestBody AddAssignmentRequest addAssignmentRequest) {
        LOGGER.info("Assignment adding => Assignment name: {}",
                addAssignmentRequest.getName());
        CommandUseCase<AddAssignmentRequest> useCase = assignmentUseCaseFactory.addAssignmentUseCase();

        useCase.execute(addAssignmentRequest);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully added Assignment"),
                HttpStatus.CREATED);
    }

}
