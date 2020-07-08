package com.sasadara.gradingapplication.dbadapterjpa.teacher;

import com.sasadara.gradingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.ports.secondary.datastore.teacher.TeacherGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPATeacherGateway implements TeacherGateway {
    private JPATeacherRepository jpaTeacherRepository;
    private ProxyFactory proxyFactory;

    @Autowired
    public JPATeacherGateway(JPATeacherRepository jpaTeacherRepository, ProxyFactory proxyFactory) {
        this.jpaTeacherRepository = jpaTeacherRepository;
        this.proxyFactory = proxyFactory;
    }

    @Override
    public void addNew(Teacher teacher) {
        TeacherProxy teacherProxy = (TeacherProxy) teacher;
        JPATeacher jpaTeacher = teacherProxy.getJPAObject();
        jpaTeacherRepository.save(jpaTeacher);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        Optional<JPATeacher> jpaTeacher = jpaTeacherRepository.findById(id);
        return jpaTeacher.map(this::createProxy);
    }

    @Override
    public Optional<Teacher> findByName(String name) {
        Optional<JPATeacher> jpaTeacher = jpaTeacherRepository.findByName(name);
        return jpaTeacher.map(this::createProxy);
    }

    @Override
    public List<Teacher> findAll() {
        return proxyAll(jpaTeacherRepository.findAll());
    }

    private Teacher createProxy(JPATeacher jpaTeacher) {
        return proxyFactory.create(TeacherProxy.class, jpaTeacher);
    }

    private List<Teacher> proxyAll(List<JPATeacher> jpaTeachers) {
        return jpaTeachers.stream()
                .map(this::createProxy)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAll(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            delete(teacher);
        }
    }


    @Override
    public void delete(Teacher teacher) {
        JPATeacher jpaTeacher = ((TeacherProxy) teacher).getJPAObject();
        jpaTeacherRepository.delete(jpaTeacher);
    }
}
