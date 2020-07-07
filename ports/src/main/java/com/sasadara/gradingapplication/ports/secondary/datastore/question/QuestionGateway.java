package com.sasadara.gradingapplication.ports.secondary.datastore.question;

import com.sasadara.gradingapplication.entities.question.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionGateway {
    void addNew(Question question);

    Optional<Question> findById(long id);

    Optional<Question> findByName(String name);

    List<Question> findAll();

    void delete(Question question);

    void deleteAll(List<Question> questions);
}
