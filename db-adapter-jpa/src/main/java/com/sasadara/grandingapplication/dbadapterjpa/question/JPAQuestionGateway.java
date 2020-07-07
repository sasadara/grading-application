package com.sasadara.grandingapplication.dbadapterjpa.question;

import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.ports.secondary.datastore.question.QuestionGateway;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPAQuestionGateway implements QuestionGateway {
    private JPAQuestionRepository jpaQuestionRepository;
    private ProxyFactory proxyFactory;

    @Autowired
    public JPAQuestionGateway(JPAQuestionRepository jpaQuestionRepository, ProxyFactory proxyFactory) {
        this.jpaQuestionRepository = jpaQuestionRepository;
        this.proxyFactory = proxyFactory;
    }

    @Override
    public void addNew(Question question) {
        QuestionProxy questionProxy = (QuestionProxy) question;
        JPAQuestion jpaQuestion = questionProxy.getJPAObject();
        jpaQuestionRepository.save(jpaQuestion);
    }

    @Override
    public Optional<Question> findById(long id) {
        Optional<JPAQuestion> jpaQuestion = jpaQuestionRepository.findById(id);
        return jpaQuestion.map(this::createProxy);
    }

    @Override
    public Optional<Question> findByName(String name) {
        Optional<JPAQuestion> jpaQuestion = jpaQuestionRepository.findByName(name);
        return jpaQuestion.map(this::createProxy);
    }

    @Override
    public List<Question> findAll() {
        return proxyAll(jpaQuestionRepository.findAll());
    }

    private Question createProxy(JPAQuestion jpaQuestion) {
        return proxyFactory.create(QuestionProxy.class, jpaQuestion);
    }

    private List<Question> proxyAll(List<JPAQuestion> jpaBinLocations) {
        return jpaBinLocations.stream()
                .map(this::createProxy)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAll(List<Question> questions) {
        for (Question question : questions) {
            delete(question);
        }
    }

    @Override
    public void delete(Question question) {
        JPAQuestion jpaQuestion = ((QuestionProxy) question).getJPAObject();
        jpaQuestionRepository.delete(jpaQuestion);
    }
}
