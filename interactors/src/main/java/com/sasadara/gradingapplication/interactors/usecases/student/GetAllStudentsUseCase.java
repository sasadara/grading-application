package com.sasadara.gradingapplication.interactors.usecases.student;


import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalFunctionUseCase;
import com.sasadara.gradingapplication.interactors.usecases.assignment.GetAllAssignmentsUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.GetAllStudentsDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.assignment.AssignmentForResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.student.GetAllStudentsDetailsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.student.GetStudentDetailsResponse;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllStudentsUseCase extends TransactionalFunctionUseCase<GetAllStudentsDetailsRequest, GetAllStudentsDetailsResponse> {
    private StudentGateway studentGateway;

    public GetAllStudentsUseCase(StudentGateway studentGateway, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.studentGateway = studentGateway;
    }

    @Override
    protected GetAllStudentsDetailsResponse executeInTransaction(GetAllStudentsDetailsRequest request) {
        GetAllStudentsDetailsResponse studentsDetailsResponse = new GetAllStudentsDetailsResponse();
        List<GetStudentDetailsResponse> studentDetailsResponseList = new LinkedList<>();
        if (request.getStudentId() != null) {
            if (!studentGateway.findById(request.getStudentId()).isPresent()) {
                throw new EntityNotFoundException(String.format("Student Id : %s is not exist", request.getStudentId()));
            }
            Student student = studentGateway.findById(request.getStudentId()).get();
            studentDetailsResponseList.add(convertStudentResponse(student));
            studentsDetailsResponse.setStudents(studentDetailsResponseList);
        } else {
            List<Student> students = studentGateway.findAll();
            studentDetailsResponseList = students.stream().map(GetAllStudentsUseCase::convertStudentResponse).collect(Collectors.toList());
            studentsDetailsResponse.setStudents(studentDetailsResponseList);
        }
        return studentsDetailsResponse;
    }

    private static GetStudentDetailsResponse convertStudentResponse(Student student) {
        GetStudentDetailsResponse studentDetailsResponse = new GetStudentDetailsResponse();
        List<AssignmentForResponse> assignmentForResponses = student.getAssignments().stream()
                .map(GetAllAssignmentsUseCase::convertAssignment).collect(Collectors.toList());
        studentDetailsResponse.setAssignments(assignmentForResponses);
        studentDetailsResponse.setId(student.getId());
        studentDetailsResponse.setName(student.getName());
        return studentDetailsResponse;
    }

}
