package com.sasadara.gradingapplication.interactors.usecases.teacher;


import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.UpdateTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

public class UpdateTeacherUseCase extends TransactionalCommandUseCase<UpdateTeacherDetailsRequest> {
    private TeacherGateway teacherGateway;

    public UpdateTeacherUseCase(TeacherGateway teacherGateway,
                                TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.teacherGateway = teacherGateway;
    }

    @Override
    protected void executeInTransaction(UpdateTeacherDetailsRequest request) {
        Teacher teacher;
        if (teacherGateway.findById(request.getId()).isPresent()) {
            teacher = teacherGateway.findById(request.getId()).get();
        } else {
            throw new EntityNotFoundException(String.format("Assignment No : %s is not exist", String.valueOf(request.getId())));
        }
        if (request.getTeacherDetailsRequest() != null && request.getTeacherDetailsRequest().getName() != null) {
            teacher.updateName(request.getTeacherDetailsRequest().getName());
        }
    }
}
