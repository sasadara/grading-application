package com.sasadara.gradingapplication.main;

import com.sasadara.gradingapplication.interactors.usecases.assignment.AddAssignmentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.assignment.GetAllAssignmentsUseCase;
import com.sasadara.gradingapplication.interactors.usecases.assignment.UpdateAssignmentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.question.AddQuestionUseCase;
import com.sasadara.gradingapplication.interactors.usecases.question.GetAllQuestionsUseCase;
import com.sasadara.gradingapplication.interactors.usecases.question.UpdateQuestionReviewUseCase;
import com.sasadara.gradingapplication.interactors.usecases.question.UpdateQuestionUseCase;
import com.sasadara.gradingapplication.interactors.usecases.student.AddStudentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.student.GetAllStudentsUseCase;
import com.sasadara.gradingapplication.interactors.usecases.student.UpdateStudentUseCase;
import com.sasadara.gradingapplication.interactors.usecases.teacher.AddTeacherUseCase;
import com.sasadara.gradingapplication.interactors.usecases.teacher.GetAllTeachersUseCase;
import com.sasadara.gradingapplication.interactors.usecases.teacher.UpdateTeacherUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.UseCaseFactories;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AddAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.GetAllAssignmentsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.UpdateAssignmentRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.AddQuestionRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.GetAllQuestionsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.question.UpdateQuestionReviewRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.AddStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.GetAllStudentsDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.UpdateStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.AddTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.GetAllTeachersDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.request.teacher.UpdateTeacherDetailsRequest;
import com.sasadara.gradingapplication.ports.primary.usecase.response.assignment.GetAllAssignmentsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.question.GetAllQuestionsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.student.GetAllStudentsDetailsResponse;
import com.sasadara.gradingapplication.ports.primary.usecase.response.teacher.GetAllTeachersDetailsResponse;
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
        return new UpdateAssignmentUseCase(dataStore.assignmentGateway(), dataStore.studentGateway(),
                dataStore.transactionalRunner());
    }

    @Override
    public FunctionUseCase<GetAllAssignmentsRequest, GetAllAssignmentsResponse> getAllAssignmentsUseCase() {
        return new GetAllAssignmentsUseCase(dataStore.assignmentGateway(), dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<AddAssignmentRequest> addAssignmentUseCase() {
        return new AddAssignmentUseCase(dataStore.assignmentGateway(), dataStore.studentGateway(), entityFactory,
                dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<UpdateQuestionRequest> updateQuestionUseCase() {
        return new UpdateQuestionUseCase(dataStore.questionGateway(), dataStore.assignmentGateway(), dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<UpdateQuestionReviewRequest> updateQuestionReviewUseCase() {
        return new UpdateQuestionReviewUseCase(dataStore.questionGateway(), dataStore.transactionalRunner());
    }

    @Override
    public FunctionUseCase<GetAllQuestionsRequest, GetAllQuestionsResponse> getAllQuestionsUseCase() {
        return new GetAllQuestionsUseCase(dataStore.questionGateway(), dataStore.transactionalRunner());
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
        return new UpdateStudentUseCase(dataStore.studentGateway(), dataStore.teacherGateway(),
                dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<AddTeacherDetailsRequest> addTeacherDetailsUseCase() {
        return new AddTeacherUseCase(dataStore.teacherGateway(), entityFactory,
                dataStore.transactionalRunner());
    }

    @Override
    public CommandUseCase<UpdateTeacherDetailsRequest> updateTeacherDetailsUseCase() {
        return new UpdateTeacherUseCase(dataStore.teacherGateway(),
                dataStore.transactionalRunner());
    }

    @Override
    public FunctionUseCase<GetAllTeachersDetailsRequest, GetAllTeachersDetailsResponse> getTeacherDetailsUseCase() {
        return new GetAllTeachersUseCase(dataStore.teacherGateway(), dataStore.transactionalRunner());
    }

    @Override
    public FunctionUseCase<GetAllStudentsDetailsRequest, GetAllStudentsDetailsResponse> getStudentDetailsUseCase() {
        return new GetAllStudentsUseCase(dataStore.studentGateway(), dataStore.transactionalRunner());
    }


}
