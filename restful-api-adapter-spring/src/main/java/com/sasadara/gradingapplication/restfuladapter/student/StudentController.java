package com.sasadara.gradingapplication.restfuladapter.student;

import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.StudentUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.AddStudentDetailsRequest;
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
@RequestMapping(path = "/student")
public class StudentController {

    private static final Logger LOGGER = getLogger(StudentController.class);
    private StudentUseCaseFactory studentUseCaseFactory;

    @Autowired
    public StudentController(StudentUseCaseFactory studentUseCaseFactory) {
        this.studentUseCaseFactory = studentUseCaseFactory;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addStudent(@Valid @RequestBody AddStudentDetailsRequest addStudentDetailsRequest) {
        LOGGER.info("Student adding => Student Name: {}",
                addStudentDetailsRequest.getName());
        CommandUseCase<AddStudentDetailsRequest> useCase = studentUseCaseFactory.addStudentDetailsUseCase();

        useCase.execute(addStudentDetailsRequest);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully added Question"),
                HttpStatus.CREATED);
    }

}
