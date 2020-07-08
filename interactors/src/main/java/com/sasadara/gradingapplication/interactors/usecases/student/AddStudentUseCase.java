package com.sasadara.gradingapplication.interactors.usecases.student;


import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.AddStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.EntityFactory;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

public class AddStudentUseCase extends TransactionalCommandUseCase<AddStudentDetailsRequest> {
    private StudentGateway studentGateway;
    private TeacherGateway teacherGateway;
    private EntityFactory entityFactory;

    public AddStudentUseCase(StudentGateway studentGateway, TeacherGateway teacherGateway,
                             EntityFactory entityFactory, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.entityFactory = entityFactory;
    }

    @Override
    protected void executeInTransaction(AddStudentDetailsRequest request) {

        Student student = entityFactory.student();
        student.updateName(request.getName());

        if (!teacherGateway.findById(request.getTeacher()).isPresent()) {
            throw new EntityNotFoundException(
                    String.format("Teacher No : %s is not exist. Cannot create student without teacher",
                            request.getTeacher()));

        }
        Teacher teacher = teacherGateway.findById(request.getTeacher()).get();
        student.updateTeacher(teacher);
        studentGateway.addNew(student);

    }
}
