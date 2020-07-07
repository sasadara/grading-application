package com.sasadara.gradingapplication.entities.assignment;

import com.sasadara.gradingapplication.entities.question.Question;

import java.util.List;

public abstract class Assignment {

    protected String name;
    protected List<Question> questions;

    public String getName() {
        return name;
    }

    public abstract void updateName(String name);

    public List<Question> getQuestions() {
        return questions;
    }

    public abstract void updateQuestions(List<Question> questions);

}
