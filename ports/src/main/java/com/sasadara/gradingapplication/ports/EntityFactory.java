package com.sasadara.gradingapplication.ports;

import com.sasadara.gradingapplication.entities.factory.AssignmentFactory;
import com.sasadara.gradingapplication.entities.factory.QuestionFactory;
import com.sasadara.gradingapplication.entities.factory.StudentFactory;
import com.sasadara.gradingapplication.entities.factory.TeacherFactory;

public interface EntityFactory extends
        AssignmentFactory, QuestionFactory, TeacherFactory, StudentFactory {
}
