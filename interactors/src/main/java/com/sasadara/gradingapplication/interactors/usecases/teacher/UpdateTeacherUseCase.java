package com.sasadara.gradingapplication.interactors.usecases.teacher;


import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.UpdateTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

import java.util.LinkedList;
import java.util.List;

public class UpdateTeacherUseCase extends TransactionalCommandUseCase<UpdateTeacherDetailsRequest> {
    private TeacherGateway teacherGateway;
    private StudentGateway studentGateway;

    public UpdateTeacherUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
                                TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
    }

    @Override
    protected void executeInTransaction(UpdateTeacherDetailsRequest request) {
        Teacher teacher;
        if (teacherGateway.findById(request.getId()).isPresent()) {
            teacher = teacherGateway.findById(request.getId()).get();
        } else {
            throw new EntityNotFoundException(String.format("Assignment No : %s is not exist", String.valueOf(request.getId())));
        }
        List<Student> students = new LinkedList<>();
        for (Long id : request.getStudents()) {
            if (studentGateway.findById(id).isPresent()) {
                students.add(studentGateway.findById(id).get());
            }
        }
        teacher.updateStudents(students);
        teacherGateway.addNew(teacher);

    }
}
