package com.sasadara.gradingapplication.dbadapterjpa.teacher;


import com.sasadara.gradingapplication.dbadapterjpa.proxy.AutoBindProxy;
import com.sasadara.gradingapplication.dbadapterjpa.proxy.Proxy;
import com.sasadara.gradingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.gradingapplication.dbadapterjpa.student.JPAStudent;
import com.sasadara.gradingapplication.dbadapterjpa.student.StudentProxy;
import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@AutoBindProxy(JPATeacher.class)
public class TeacherProxy extends Teacher implements Proxy<JPATeacher> {

    private JPATeacher jpaTeacher;
    private ProxyFactory proxyFactory;

    public TeacherProxy(JPATeacher jpaTeacher) {
        this.jpaTeacher = jpaTeacher;
    }

    @Autowired
    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public JPATeacher getJPAObject() {
        return jpaTeacher;
    }

    @Override
    public Long getId() {
        return jpaTeacher.getId();
    }

    @Override
    public String getName() {
        return jpaTeacher.getName();
    }

    public List<Student> getStudents() {
        return jpaTeacher.getJpaStudents().stream()
                .map(this::getStudentJPAForm)
                .collect(Collectors.toList());
    }

    public Student getStudentJPAForm(JPAStudent jpaStudent) {
        return proxyFactory.create(StudentProxy.class, jpaStudent);
    }

    @Override
    public void updateName(String name) {
        jpaTeacher.setName(name);
    }

    @Override
    public void updateStudents(List<Student> students) {
        List<JPAStudent> jpaStudent = students.stream()
                .map(student -> ((StudentProxy) student).getJPAObject())
                .collect(Collectors.toList());

        jpaTeacher.setJpaStudents(jpaStudent);
    }
}
