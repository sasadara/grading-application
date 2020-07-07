package com.sasadara.gradingapplication.entities.student;

import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.teacher.Teacher;

import java.util.List;
import java.util.Map;

public abstract class Student {

    protected String name;
    protected List<Assignment> assignments;
    protected Teacher teacher;

    public String getName() {
        return name;
    }

    public abstract void updateName(String name);

    public Teacher getTeacher() {
        return teacher;
    }

    public abstract void updateTeacher(Teacher teacher);

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public abstract void updateAssignments(List<Assignment> assignments);

    public abstract double getAvgTime();

    public abstract Map<String, Double> getCorrectIncorrectAnswers();

}
