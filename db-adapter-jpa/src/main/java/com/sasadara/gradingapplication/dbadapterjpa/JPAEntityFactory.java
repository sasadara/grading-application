package com.sasadara.gradingapplication.dbadapterjpa;


import com.sasadara.gradingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.gradingapplication.dbadapterjpa.student.JPAStudent;
import com.sasadara.gradingapplication.dbadapterjpa.student.StudentProxy;
import com.sasadara.gradingapplication.dbadapterjpa.teacher.JPATeacher;
import com.sasadara.gradingapplication.dbadapterjpa.teacher.TeacherProxy;
import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.question.Question;
import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.entities.teacher.Teacher;
import com.sasadara.gradingapplication.ports.secondary.datastore.EntityFactory;
import com.sasadara.gradingapplication.dbadapterjpa.assignment.AssignmentProxy;
import com.sasadara.gradingapplication.dbadapterjpa.assignment.JPAAssignment;
import com.sasadara.gradingapplication.dbadapterjpa.question.JPAQuestion;
import com.sasadara.gradingapplication.dbadapterjpa.question.QuestionProxy;
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
