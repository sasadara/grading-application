package com.sasadara.gradingapplication.dbadapterjpa.question;


import com.sasadara.gradingapplication.dbadapterjpa.assignment.AssignmentProxy;
import com.sasadara.gradingapplication.dbadapterjpa.proxy.AutoBindProxy;
import com.sasadara.gradingapplication.dbadapterjpa.proxy.Proxy;
import com.sasadara.gradingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.entities.question.Results;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;

@AutoBindProxy(JPAQuestion.class)
public class QuestionProxy extends Question implements Proxy<JPAQuestion> {

    private JPAQuestion jpaQuestion;
    private ProxyFactory proxyFactory;

    public QuestionProxy(JPAQuestion jpaQuestion) {
        this.jpaQuestion = jpaQuestion;
    }

    @Autowired
    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public JPAQuestion getJPAObject() {
        return jpaQuestion;
    }

    @Override
    public long getId() {
        return jpaQuestion.getId();
    }

    @Override
    public void updateId(long id) {
        jpaQuestion.setId(id);
    }

    @Override
    public String getName() {
        return jpaQuestion.getName();
    }

    @Override
    public void updateName(String name) {
        jpaQuestion.setName(name);
    }

    @Override
    public Assignment getAssignment() {
        return proxyFactory.create(AssignmentProxy.class, jpaQuestion.getJpaAssignment());
    }

    @Override
    public void updateAssignment(Assignment assignment) {
        jpaQuestion.setJpaAssignment(((AssignmentProxy) assignment).getJPAObject());
    }

    @Override
    public Results getResult() {
        return jpaQuestion.getResult();
    }

    @Override
    public void updateResult(String value) {
        Results results = Results.from(value);
        jpaQuestion.setResult(results);
    }

    @Override
    public int getTimeSpentMints() {
        return jpaQuestion.getTimeSpentMints();
    }

    @Override
    public void updateTimeSpentMints(int timeSpentMints) {
        if (timeSpentMints < 1) {
            throw new InvalidRequestException("Time spend per question should be always greater than 0");
        }
        jpaQuestion.setTimeSpentMints(timeSpentMints);
    }

    @Override
    public int getNumberOfAttempts() {
        return jpaQuestion.getNumberOfAttempts();
    }

    @Override
    public void updateNumberOfAttempts(int numberOfAttempts) {
        if (numberOfAttempts < 1) {
            throw new InvalidRequestException("Number of attempts should be always greater than 0");
        }
        jpaQuestion.setNumberOfAttempts(numberOfAttempts);
    }

    @Override
    public String getReview() {
        return jpaQuestion.getReview();
    }

    @Override
    public void updateReview(String review) {
        jpaQuestion.setReview(review);
    }
}
