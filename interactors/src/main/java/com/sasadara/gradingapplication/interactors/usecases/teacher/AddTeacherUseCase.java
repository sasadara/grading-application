package com.sasadara.gradingapplication.interactors.usecases.teacher;


import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.EntityFactory;
import com.sasadara.gradingapplication.ports.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.AddTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

import java.util.LinkedList;
import java.util.List;

public class AddTeacherUseCase extends TransactionalCommandUseCase<AddTeacherDetailsRequest> {
    private StudentGateway studentGateway;
    private EntityFactory entityFactory;
    private TeacherGateway teacherGateway;

    public AddTeacherUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
                             EntityFactory entityFactory, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.entityFactory = entityFactory;
    }

    @Override
    protected void executeInTransaction(AddTeacherDetailsRequest request) {

        Teacher teacher = entityFactory.teacher();
        teacher.updateName(request.getName());

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
