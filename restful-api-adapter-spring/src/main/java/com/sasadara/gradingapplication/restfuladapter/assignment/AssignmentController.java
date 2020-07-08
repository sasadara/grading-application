package com.sasadara.gradingapplication.restfuladapter.assignment;

import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.AssignmentUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AddAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.GetAllAssignmentsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.UpdateAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.assignment.GetAllAssignmentsResponse;
import com.sasadara.gradingapplication.restfuladapter.response.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<ResponseWrapper<GetAllAssignmentsResponse>> getAllAssignments(@Valid @RequestParam(required = false) Long assignmentId) {

        FunctionUseCase<GetAllAssignmentsRequest, GetAllAssignmentsResponse> useCase
                = assignmentUseCaseFactory.getAllAssignmentsUseCase();

        GetAllAssignmentsRequest request = new GetAllAssignmentsRequest(assignmentId);
        GetAllAssignmentsResponse response = useCase.execute(request);

        return new ResponseEntity<>(new ResponseWrapper<>(response), HttpStatus.OK);
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

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> updateAssignment(@PathVariable(name = "id") Long assignmentId,
                                                                    @Valid @RequestBody AssignmentRequest assignmentRequest) {
        LOGGER.info("Assignment updating => Assignment id: {}",
                assignmentId);
        CommandUseCase<UpdateAssignmentRequest> useCase = assignmentUseCaseFactory.updateAssignmentUseCase();
        UpdateAssignmentRequest request = new UpdateAssignmentRequest();
        request.setId(assignmentId);
        request.setAssignmentRequest(assignmentRequest);
        useCase.execute(request);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully update Assignment"),
                HttpStatus.CREATED);
    }

}
