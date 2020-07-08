package com.sasadara.gradingapplication.restfuladapter.student;

import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.StudentUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.AddStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.GetAllStudentsDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.StudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.UpdateStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.student.GetAllStudentsDetailsResponse;
import com.sasadara.gradingapplication.restfuladapter.response.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<ResponseWrapper<GetAllStudentsDetailsResponse>> getAllStudents(@Valid @RequestParam(required = false) Long studentId) {
        FunctionUseCase<GetAllStudentsDetailsRequest, GetAllStudentsDetailsResponse> useCase
                = studentUseCaseFactory.getStudentDetailsUseCase();

        GetAllStudentsDetailsRequest request = new GetAllStudentsDetailsRequest(studentId);
        GetAllStudentsDetailsResponse response = useCase.execute(request);

        return new ResponseEntity<>(new ResponseWrapper<>(response), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addStudent(@Valid @RequestBody AddStudentDetailsRequest addStudentDetailsRequest) {
        LOGGER.info("Student adding => Student Name: {}",
                addStudentDetailsRequest.getName());
        CommandUseCase<AddStudentDetailsRequest> useCase = studentUseCaseFactory.addStudentDetailsUseCase();

        useCase.execute(addStudentDetailsRequest);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully added Student"),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> updateStudent(@PathVariable(name = "id") Long studentId,
                                                                 @Valid @RequestBody StudentDetailsRequest studentDetailsRequest) {
        LOGGER.info("Student updating => Student Id: {}",
                studentId);
        CommandUseCase<UpdateStudentDetailsRequest> useCase = studentUseCaseFactory.updateStudentDetailsUseCase();
        UpdateStudentDetailsRequest request = new UpdateStudentDetailsRequest();
        request.setId(studentId);
        request.setStudentDetailsRequest(studentDetailsRequest);
        useCase.execute(request);

        return new ResponseEntity<>(new ResponseWrapper<>("Successfully Update Student"),
                HttpStatus.CREATED);
    }

}
