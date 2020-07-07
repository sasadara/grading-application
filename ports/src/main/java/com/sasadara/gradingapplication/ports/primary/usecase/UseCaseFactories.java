package com.sasadara.gradingapplication.ports.primary.usecase;

import com.sasadara.gradingapplication.ports.primary.usecase.factory.AssignmentUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.QuestionUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.StudentUseCaseFactory;
import com.sasadara.gradingapplication.ports.primary.usecase.factory.TeacherUseCaseFactory;

public interface UseCaseFactories extends
        AssignmentUseCaseFactory, QuestionUseCaseFactory, StudentUseCaseFactory, TeacherUseCaseFactory {
}
