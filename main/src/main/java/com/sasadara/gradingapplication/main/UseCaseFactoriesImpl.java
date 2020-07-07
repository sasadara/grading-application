package com.sasadara.gradingapplication.main;

import com.sasadara.gradingapplication.interactors.usecases.assignment.AddAssignmentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.assignment.UpdateAssignmentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.question.AddQuestionUseCase;
import com.sasadara.gradingapplication.interactors.usecases.question.UpdateQuestionUseCase;
import com.sasadara.gradingapplication.interactors.usecases.student.AddStudentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.student.GetStudentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.student.UpdateStudentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.teacher.AddTeacherUseCase;
import com.sasadara.gradingapplication.interactors.usecases.teacher.GetTeacherUseCase;
import com.sasadara.gradingapplication.interactors.usecases.teacher.UpdateTeacherUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.UseCaseFactories;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AddAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.UpdateAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.AddQuestionRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.AddStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.GetStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.UpdateStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.AddTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.GetTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.UpdateTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.student.GetStudentDetailsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.GetTeacherDetailsResponse;
import com.sasadara.gradingapplication.ports.secondary.datastore.DataStore;
import com.sasadara.gradingapplication.ports.secondary.datastore.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UseCaseFactoriesImpl implements UseCaseFactories {
    private DataStore dataStore;
    private EntityFactory entityFactory;

    @Autowired
    public UseCaseFactoriesImpl(DataStore dataStore, EntityFactory entityFactory) {
        this.dataStore = dataStore;
        this.entityFactory = entityFactory;
    }

    @Override
    public CommandUseCase<UpdateAssignmentRequest> updateAssignmentUseCase() {
        return new UpdateAssignmentUseCase(dataStore.assignmentGateway(), dataStore.questionGateway(),
                dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<AddAssignmentRequest> addAssignmentUseCase() {
        return new AddAssignmentUseCase(dataStore.assignmentGateway(), dataStore.studentGateway(), entityFactory,
                dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<UpdateQuestionRequest> updateQuestionUseCase() {
        return new UpdateQuestionUseCase(dataStore.questionGateway(), dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<AddQuestionRequest> addQuestionUseCase() {
        return new AddQuestionUseCase(dataStore.questionGateway(), dataStore.assignmentGateway(),
                entityFactory, dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<AddStudentDetailsRequest> addStudentDetailsUseCase() {
        return new AddStudentUseCase(dataStore.studentGateway(), dataStore.teacherGateway(),
                entityFactory, dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<UpdateStudentDetailsRequest> updateStudentDetailsUseCase() {
        return new UpdateStudentUseCase(dataStore.assignmentGateway(), dataStore.studentGateway(),
                dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<AddTeacherDetailsRequest> addTeacherDetailsUseCase() {
        return new AddTeacherUseCase(dataStore.teacherGateway(), entityFactory,
                dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<UpdateTeacherDetailsRequest> updateTeacherDetailsUseCase() {
        return new UpdateTeacherUseCase(dataStore.teacherGateway(), dataStore.studentGateway(),
                dataStore.transactionalRunner());
    }

    @Override
    public FunctionUseCase<GetTeacherDetailsRequest, GetTeacherDetailsResponse> getTeacherDetailsUseCase() {
        return new GetTeacherUseCase(dataStore.teacherGateway(), dataStore.transactionalRunner());
    }

    @Override
    public FunctionUseCase<GetStudentDetailsRequest, GetStudentDetailsResponse> getStudentDetailsUseCase() {
        return new GetStudentUseCase(dataStore.studentGateway(), dataStore.transactionalRunner());
    }


}
