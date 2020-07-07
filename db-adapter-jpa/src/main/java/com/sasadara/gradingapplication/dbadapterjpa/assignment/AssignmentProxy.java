package com.sasadara.gradingapplication.dbadapterjpa.assignment;

import com.sasadara.gradingapplication.dbadapterjpa.proxy.AutoBindProxy;
import com.sasadara.gradingapplication.dbadapterjpa.proxy.Proxy;
import com.sasadara.gradingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.gradingapplication.dbadapterjpa.question.JPAQuestion;
import com.sasadara.gradingapplication.dbadapterjpa.question.QuestionProxy;
import com.sasadara.gradingapplication.dbadapterjpa.student.StudentProxy;
import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.entities.student.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@AutoBindProxy(JPAAssignment.class)
public class AssignmentProxy extends Assignment implements Proxy<JPAAssignment> {

    private JPAAssignment jpaAssignment;
    private ProxyFactory proxyFactory;

    public AssignmentProxy(JPAAssignment jpaAssignment) {
        this.jpaAssignment = jpaAssignment;
    }

    @Autowired
    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public JPAAssignment getJPAObject() {
        return jpaAssignment;
    }

    @Override
    public String getName() {
        return jpaAssignment.getName();
    }

    @Override
    public void updateName(String name) {
        jpaAssignment.setName(name);
    }

    @Override
    public List<Question> getQuestions() {
        return jpaAssignment.getJpaQuestions().stream()
                .map(this::getQuestionJPAForm)
                .collect(Collectors.toList());
    }

    @Override
    public Student getStudent() {
        return proxyFactory.create(StudentProxy.class, jpaAssignment.getJpaStudent());
    }

    @Override
    public void updateStudent(Student student) {
        jpaAssignment.setJpaStudent(((StudentProxy) student).getJPAObject());
    }

    public Question getQuestionJPAForm(JPAQuestion jpaQuestion) {
        return proxyFactory.create(QuestionProxy.class, jpaQuestion);
    }

    @Override
    public void updateQuestions(List<Question> questions) {
        List<JPAQuestion> jpaQuestions = questions.stream()
                .map(question -> ((QuestionProxy) question).getJPAObject())
                .collect(Collectors.toList());

        jpaAssignment.setJpaQuestions(jpaQuestions);
    }
}
