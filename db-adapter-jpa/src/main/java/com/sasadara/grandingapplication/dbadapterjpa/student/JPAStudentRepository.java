package com.sasadara.grandingapplication.dbadapterjpa.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAStudentRepository extends JpaRepository<JPAStudent, Long> {
    Optional<JPAStudent> findByName(String name);
}
