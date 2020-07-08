package com.sasadara.gradingapplication.entities.teacher;

import com.sasadara.gradingapplication.entities.student.Student;

import java.util.List;

public abstract class Teacher {
    protected String name;
    protected List<Student> students;
    protected Long id;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void updateName(String name);

    public List<Student> getStudents() {
        return students;
    }

    public abstract void updateStudents(List<Student> students);
}
