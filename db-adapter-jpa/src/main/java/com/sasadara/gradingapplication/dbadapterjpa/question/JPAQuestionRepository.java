package com.sasadara.gradingapplication.dbadapterjpa.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAQuestionRepository extends JpaRepository<JPAQuestion, Long> {
    Optional<JPAQuestion> findByName(String name);
}
