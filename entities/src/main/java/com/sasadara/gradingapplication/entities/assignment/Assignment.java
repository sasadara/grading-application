package com.sasadara.gradingapplication.entities.assignment;

import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.entities.student.Student;

import java.util.List;

public abstract class Assignment {

    protected String name;
    protected List<Question> questions;
    protected Student student;
    protected Long id;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void updateName(String name);

    public Student getStudent() {
        return student;
    }

    public abstract void updateStudent(Student student);

    public List<Question> getQuestions() {
        return questions;
    }

    public abstract void updateQuestions(List<Question> questions);

}
