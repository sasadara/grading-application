package com.sasadara.gradingapplication.ports.secondary.datastore.student;

import com.sasadara.gradingapplication.entities.student.Student;

import java.util.List;
import java.util.Optional;

public interface StudentGateway {
    void addNew(Student student);

    Optional<Student> findById(Long id);

    Optional<Student> findByName(String name);

    List<Student> findAll();

    void delete(Student student);

    void deleteAll(List<Student> students);
}
