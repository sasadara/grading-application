package com.sasadara.gradingapplication.ports.secondary.datastore.assignment;

import com.sasadara.gradingapplication.entities.assignment.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentGateway {
    void addNew(Assignment assignment);

    Optional<Assignment> findById(Long id);

    Optional<Assignment> findByName(String name);

    List<Assignment> findAll();

    void delete(Assignment assignment);

    void deleteAll(List<Assignment> assignments);
}
