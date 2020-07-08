package com.sasadara.gradingapplication.dbadapterjpa.student;

import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;
import com.sasadara.gradingapplication.dbadapterjpa.proxy.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPAStudentGateway implements StudentGateway {
    private JPAStudentRepository jpaStudentRepository;
    private ProxyFactory proxyFactory;

    @Autowired
    public JPAStudentGateway(JPAStudentRepository jpaStudentRepository, ProxyFactory proxyFactory) {
        this.jpaStudentRepository = jpaStudentRepository;
        this.proxyFactory = proxyFactory;
    }

    @Override
    public void addNew(Student student) {
        StudentProxy studentProxy = (StudentProxy) student;
        JPAStudent jpaStudent = studentProxy.getJPAObject();
        jpaStudentRepository.save(jpaStudent);
    }

    @Override
    public Optional<Student> findById(Long id) {
        Optional<JPAStudent> jpaStudent = jpaStudentRepository.findById(id);
        return jpaStudent.map(this::createProxy);
    }

    @Override
    public Optional<Student> findByName(String name) {
        Optional<JPAStudent> jpaStudent = jpaStudentRepository.findByName(name);
        return jpaStudent.map(this::createProxy);
    }

    @Override
    public List<Student> findAll() {
        return proxyAll(jpaStudentRepository.findAll());
    }

    private Student createProxy(JPAStudent jpaStudent) {
        return proxyFactory.create(StudentProxy.class, jpaStudent);
    }

    private List<Student> proxyAll(List<JPAStudent> jpaStudents) {
        return jpaStudents.stream()
                .map(this::createProxy)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAll(List<Student> students) {
        for (Student student : students) {
            delete(student);
        }
    }


    @Override
    public void delete(Student student) {
        JPAStudent jpaStudent = ((StudentProxy) student).getJPAObject();
        jpaStudentRepository.delete(jpaStudent);
    }
}
