package com.sasadara.gradingapplication.dbadapterjpa;


import com.sasadara.gradingapplication.ports.secondary.datastore.DataStore;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JPADataStore implements DataStore {
    private TransactionalRunner transactionalRunner;
    private AssignmentGateway assignmentGateway;
    private QuestionGateway questionGateway;
    private TeacherGateway teacherGateway;
    private StudentGateway studentGateway;


    @Autowired
    public JPADataStore(TransactionalRunner transactionalRunner, AssignmentGateway assignmentGateway, QuestionGateway questionGateway,
                        TeacherGateway teacherGateway, StudentGateway studentGateway) {
        this.transactionalRunner = transactionalRunner;
        this.assignmentGateway = assignmentGateway;
        this.questionGateway = questionGateway;
        this.studentGateway = studentGateway;
        this.teacherGateway = teacherGateway;

    }

    @Override
    public TransactionalRunner transactionalRunner() {
        return transactionalRunner;
    }

    @Override
    public AssignmentGateway assignmentGateway() {
        return assignmentGateway;
    }

    @Override
    public QuestionGateway questionGateway() {
        return questionGateway;
    }

    @Override
    public TeacherGateway teacherGateway() {
        return teacherGateway;
    }

    @Override
    public StudentGateway studentGateway() {
        return studentGateway;
    }

}
