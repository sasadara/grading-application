package com.sasadara.gradingapplication.restfuladapter.teacher;

import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.TeacherUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.AddTeacherDetailsRequest;
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
@RequestMapping(path = "/teacher")
public class TeacherController {

    private static final Logger LOGGER = getLogger(TeacherController.class);
    private TeacherUseCaseFactory teacherUseCaseFactory;

    @Autowired
    public TeacherController(TeacherUseCaseFactory teacherUseCaseFactory) {
        this.teacherUseCaseFactory = teacherUseCaseFactory;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addTeacher(@Valid @RequestBody AddTeacherDetailsRequest addTeacherDetailsRequest) {
        LOGGER.info("Teacher adding => Teacher Name: {}",
                addTeacherDetailsRequest.getName());
        CommandUseCase<AddTeacherDetailsRequest> useCase = teacherUseCaseFactory.addTeacherDetailsUseCase();

        useCase.execute(addTeacherDetailsRequest);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully added Question"),
                HttpStatus.CREATED);
    }

}
