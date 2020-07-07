package com.sasadara.gradingapplication.ports.secondary.datastore;

import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;

public interface DataStore {
    TransactionalRunner transactionalRunner();

    AssignmentGateway assignmentGateway();

    QuestionGateway questionGateway();

    TeacherGateway teacherGateway();

    StudentGateway studentGateway();
}
