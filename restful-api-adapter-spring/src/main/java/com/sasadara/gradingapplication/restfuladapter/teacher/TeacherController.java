package com.sasadara.gradingapplication.restfuladapter.teacher;

import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.TeacherUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.GetStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.AddTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.GetTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.UpdateTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.student.GetStudentDetailsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.GetTeacherDetailsResponse;
import com.sasadara.gradingapplication.restfuladapter.response.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<ResponseWrapper<GetTeacherDetailsResponse>> getTeacher(@Valid @RequestParam Long teacherId) {
        LOGGER.info("Get Object request received. Student ID: {}", teacherId);
        FunctionUseCase<GetTeacherDetailsRequest, GetTeacherDetailsResponse> useCase
                = teacherUseCaseFactory.getTeacherDetailsUseCase();

        GetTeacherDetailsRequest request = new GetTeacherDetailsRequest(teacherId);
        GetTeacherDetailsResponse response = useCase.execute(request);

        return new ResponseEntity<>(new ResponseWrapper<>(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addTeacher(@Valid @RequestBody AddTeacherDetailsRequest addTeacherDetailsRequest) {
        LOGGER.info("Teacher adding => Teacher Name: {}",
                addTeacherDetailsRequest.getName());
        CommandUseCase<AddTeacherDetailsRequest> useCase = teacherUseCaseFactory.addTeacherDetailsUseCase();

        useCase.execute(addTeacherDetailsRequest);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully added Teacher"),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<String>> updateTeacher(@Valid @RequestBody UpdateTeacherDetailsRequest updateTeacherDetailsRequest) {
        LOGGER.info("Teacher adding => Teacher Id: {}",
                updateTeacherDetailsRequest.getId());
        CommandUseCase<UpdateTeacherDetailsRequest> useCase = teacherUseCaseFactory.updateTeacherDetailsUseCase();

        useCase.execute(updateTeacherDetailsRequest);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully Update Teacher"),
                HttpStatus.CREATED);
    }

}
