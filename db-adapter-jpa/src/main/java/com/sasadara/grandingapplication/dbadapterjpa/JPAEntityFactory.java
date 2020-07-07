package com.sasadara.grandingapplication.dbadapterjpa;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.ports.EntityFactory;
import com.sasadara.grandingapplication.dbadapterjpa.assignment.AssignmentProxy;
import com.sasadara.grandingapplication.dbadapterjpa.assignment.JPAAssignment;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.grandingapplication.dbadapterjpa.question.JPAQuestion;
import com.sasadara.grandingapplication.dbadapterjpa.question.QuestionProxy;
import com.sasadara.grandingapplication.dbadapterjpa.student.JPAStudent;
import com.sasadara.grandingapplication.dbadapterjpa.student.StudentProxy;
import com.sasadara.grandingapplication.dbadapterjpa.teacher.JPATeacher;
import com.sasadara.grandingapplication.dbadapterjpa.teacher.TeacherProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JPAEntityFactory implements EntityFactory {
    private ProxyFactory proxyFactory;

    @Autowired
    public JPAEntityFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public Assignment assignment() {
        return proxyFactory.create(AssignmentProxy.class, new JPAAssignment());
    }

    @Override
    public Question question() {
        return proxyFactory.create(QuestionProxy.class, new JPAQuestion());
    }

    @Override
    public Student student() {
        return proxyFactory.create(StudentProxy.class, new JPAStudent());
    }

    @Override
    public Teacher teacher() {
        return proxyFactory.create(TeacherProxy.class, new JPATeacher());
    }
}
