package com.sasadara.gradingapplication.interactors.usecases.student;


import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.StudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.UpdateStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

public class UpdateStudentUseCase extends TransactionalCommandUseCase<UpdateStudentDetailsRequest> {
    private TeacherGateway teacherGateway;
    private StudentGateway studentGateway;

    public UpdateStudentUseCase(StudentGateway studentGateway, TeacherGateway teacherGateway,
                                TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
    }

    @Override
    protected void executeInTransaction(UpdateStudentDetailsRequest request) {
        Student student;
        if (studentGateway.findById(request.getId()).isPresent()) {
            student = studentGateway.findById(request.getId()).get();
        } else {
            throw new EntityNotFoundException(String.format("Student No : %s is not exist", request.getId()));
        }
        if (request.getStudentDetailsRequest() != null) {
            StudentDetailsRequest studentDetailsRequest = request.getStudentDetailsRequest();
            if (studentDetailsRequest.getName() != null) {
                student.updateName(studentDetailsRequest.getName());
            }
            if (studentDetailsRequest.getTeacherId() != null && teacherGateway.findById(studentDetailsRequest.getTeacherId()).isPresent()) {
                Teacher teacher = teacherGateway.findById(studentDetailsRequest.getTeacherId()).get();
                student.updateTeacher(teacher);
            }
        }
    }
}
