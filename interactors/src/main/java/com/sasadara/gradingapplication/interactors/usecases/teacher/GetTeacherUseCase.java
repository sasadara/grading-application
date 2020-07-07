package com.sasadara.gradingapplication.interactors.usecases.teacher;


import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalFunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.GetTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.GetTeacherDetailsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.StudentForResponse;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

import java.util.LinkedList;
import java.util.List;

public class GetTeacherUseCase extends TransactionalFunctionUseCase<GetTeacherDetailsRequest, GetTeacherDetailsResponse> {
    private TeacherGateway teacherGateway;

    public GetTeacherUseCase(TeacherGateway teacherGateway, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.teacherGateway = teacherGateway;
    }

    @Override
    protected GetTeacherDetailsResponse executeInTransaction(GetTeacherDetailsRequest request) {

        if (!teacherGateway.findById(request.getTeacherId()).isPresent()) {
            throw new EntityNotFoundException(String.format("Teacher Id : %s is not exist", request.getTeacherId()));
        }

        Teacher teacher = teacherGateway.findById(request.getTeacherId()).get();

        GetTeacherDetailsResponse teacherDetailsResponse = new GetTeacherDetailsResponse();

        List<StudentForResponse> students = new LinkedList<>();
        for (Student student : teacher.getStudents()) {
            StudentForResponse studentForResponse = new StudentForResponse();
            studentForResponse.setName(student.getName());
            studentForResponse.setAvgTime(student.getAvgTime());
            studentForResponse.setCorrectAnswers(student.getCorrectIncorrectAnswers().get("correct"));
            studentForResponse.setIncorrectAnswers(student.getCorrectIncorrectAnswers().get("incorrect"));
            students.add(studentForResponse);
        }
        teacherDetailsResponse.setStudents(students);
        return teacherDetailsResponse;
    }
}
