package com.sasadara.gradingapplication.entities.student;

import com.sasadara.gradingapplication.entities.assignment.Assignment;

import java.util.List;
import java.util.Map;

public abstract class Student {

    protected String name;
    protected List<Assignment> assignments;

    public String getName() {
        return name;
    }

    public abstract void updateName(String name);

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public abstract void updateAssignments(List<Assignment> assignments);

    public abstract double getAvgTime();

    public abstract Map<String, Double> getCorrectIncorrectAnswers();

}
