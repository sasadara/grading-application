package com.sasadara.gradingapplication.interactors.usecases.teacher;


import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.AddTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.EntityFactory;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

public class AddTeacherUseCase extends TransactionalCommandUseCase<AddTeacherDetailsRequest> {
    private EntityFactory entityFactory;
    private TeacherGateway teacherGateway;

    public AddTeacherUseCase(TeacherGateway teacherGateway,
                             EntityFactory entityFactory, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.teacherGateway = teacherGateway;
        this.entityFactory = entityFactory;
    }

    @Override
    protected void executeInTransaction(AddTeacherDetailsRequest request) {

        Teacher teacher = entityFactory.teacher();
        teacher.updateName(request.getName());
        teacherGateway.addNew(teacher);

    }
}
