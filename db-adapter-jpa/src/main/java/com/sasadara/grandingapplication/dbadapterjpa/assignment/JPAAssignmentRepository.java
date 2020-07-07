package com.sasadara.grandingapplication.dbadapterjpa.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAAssignmentRepository extends JpaRepository<JPAAssignment, Long> {
    Optional<JPAAssignment> findByName(String name);
}
