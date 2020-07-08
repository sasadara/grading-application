package com.sasadara.gradingapplication.ports.secondary.datastore.teacher;

import com.sasadara.gradingapplication.entities.teacher.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherGateway {
    void addNew(Teacher teacher);

    Optional<Teacher> findById(Long id);

    Optional<Teacher> findByName(String name);

    List<Teacher> findAll();

    void delete(Teacher teacher);

    void deleteAll(List<Teacher> teachers);
}
