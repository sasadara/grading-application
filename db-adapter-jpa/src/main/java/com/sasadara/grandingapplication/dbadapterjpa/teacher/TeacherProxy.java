package com.sasadara.grandingapplication.dbadapterjpa.teacher;


import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.AutoBindProxy;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.Proxy;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.grandingapplication.dbadapterjpa.student.JPAStudent;
import com.sasadara.grandingapplication.dbadapterjpa.student.StudentProxy;
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
    public String getName() {
        return jpaTeacher.getName();
    }

    public List<Student> getStudents() {
        return jpaTeacher.getStudents().stream()
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

        jpaTeacher.setStudents(jpaStudent);
    }
}
