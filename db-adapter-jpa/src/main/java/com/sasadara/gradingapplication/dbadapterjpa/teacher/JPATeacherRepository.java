package com.sasadara.gradingapplication.dbadapterjpa.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPATeacherRepository extends JpaRepository<JPATeacher, Long> {
    Optional<JPATeacher> findByName(String name);
}
