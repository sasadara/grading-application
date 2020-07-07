package com.sasadara.grandingapplication.dbadapterjpa.question;


import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.entities.question.Results;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.AutoBindProxy;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.Proxy;

@AutoBindProxy(JPAQuestion.class)
public class QuestionProxy extends Question implements Proxy<JPAQuestion> {

    private JPAQuestion jpaQuestion;

    public QuestionProxy(JPAQuestion jpaQuestion) {
        this.jpaQuestion = jpaQuestion;
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
        jpaQuestion.setTimeSpentMints(timeSpentMints);
    }

    @Override
    public int getNumberOfAttempts() {
        return jpaQuestion.getNumberOfAttempts();
    }

    @Override
    public void updateNumberOfAttempts(int numberOfAttempts) {
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
