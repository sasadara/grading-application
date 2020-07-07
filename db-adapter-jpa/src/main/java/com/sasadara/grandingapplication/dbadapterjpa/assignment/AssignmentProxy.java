package com.sasadara.grandingapplication.dbadapterjpa.assignment;

import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.AutoBindProxy;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.Proxy;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.grandingapplication.dbadapterjpa.question.JPAQuestion;
import com.sasadara.grandingapplication.dbadapterjpa.question.QuestionProxy;
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
        return jpaAssignment.getQuestions().stream()
                .map(this::getQuestionJPAForm)
                .collect(Collectors.toList());
    }

    public Question getQuestionJPAForm(JPAQuestion jpaQuestion) {
        return proxyFactory.create(QuestionProxy.class, jpaQuestion);
    }

    @Override
    public void updateQuestions(List<Question> questions) {
        List<JPAQuestion> jpaQuestions = questions.stream()
                .map(question -> ((QuestionProxy) question).getJPAObject())
                .collect(Collectors.toList());

        jpaAssignment.setQuestions(jpaQuestions);
    }
}
