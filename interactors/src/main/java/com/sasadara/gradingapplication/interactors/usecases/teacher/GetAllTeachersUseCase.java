package com.sasadara.gradingapplication.interactors.usecases.teacher;


import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalFunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.GetAllTeachersDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.GetAllTeachersDetailsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.GetTeacherDetailsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.StudentForResponse;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllTeachersUseCase extends TransactionalFunctionUseCase<GetAllTeachersDetailsRequest, GetAllTeachersDetailsResponse> {
    private TeacherGateway teacherGateway;

    public GetAllTeachersUseCase(TeacherGateway teacherGateway, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.teacherGateway = teacherGateway;
    }

    @Override
    protected GetAllTeachersDetailsResponse executeInTransaction(GetAllTeachersDetailsRequest request) {

        GetAllTeachersDetailsResponse teachersDetailsResponse = new GetAllTeachersDetailsResponse();
        List<GetTeacherDetailsResponse> teacherDetailsResponsesList = new LinkedList<>();
        if (request.getTeacherId() != null) {
            if (!teacherGateway.findById(request.getTeacherId()).isPresent()) {
                throw new EntityNotFoundException(String.format("Teacher Id : %s is not exist", request.getTeacherId()));
            }
            Teacher teacher = teacherGateway.findById(request.getTeacherId()).get();
            teacherDetailsResponsesList.add(convertTeacherResponse(teacher));
            teachersDetailsResponse.setTeachers(teacherDetailsResponsesList);
        } else {
            List<Teacher> teachers = teacherGateway.findAll();
            teacherDetailsResponsesList = teachers.stream().map(GetAllTeachersUseCase::convertTeacherResponse).collect(Collectors.toList());
            teachersDetailsResponse.setTeachers(teacherDetailsResponsesList);
        }
        return teachersDetailsResponse;
    }


    private static GetTeacherDetailsResponse convertTeacherResponse(Teacher teacher) {
        GetTeacherDetailsResponse teacherDetailsResponse = new GetTeacherDetailsResponse();
        List<StudentForResponse> students = new LinkedList<>();
        for (Student student : teacher.getStudents()) {
            StudentForResponse studentForResponse = new StudentForResponse();
            studentForResponse.setName(student.getName());
            studentForResponse.setAvgTime(student.getAvgPerAssignmentTime());
            studentForResponse.setCorrectAnswers(student.getCorrectIncorrectAnswers().get("correct"));
            studentForResponse.setIncorrectAnswers(student.getCorrectIncorrectAnswers().get("incorrect"));
            students.add(studentForResponse);
        }
        teacherDetailsResponse.setId(teacher.getId());
        teacherDetailsResponse.setName(teacher.getName());
        teacherDetailsResponse.setStudents(students);
        return teacherDetailsResponse;
    }

}
