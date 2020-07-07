package com.sasadara.grandingapplication.dbadapterjpa.student;

import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.grandingapplication.dbadapterjpa.assignment.AssignmentProxy;
import com.sasadara.grandingapplication.dbadapterjpa.assignment.JPAAssignment;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.AutoBindProxy;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.Proxy;
import com.sasadara.grandingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.grandingapplication.dbadapterjpa.question.JPAQuestion;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@AutoBindProxy(JPAStudent.class)
public class StudentProxy extends Student implements Proxy<JPAStudent> {

    private JPAStudent jpaStudent;
    private ProxyFactory proxyFactory;

    public StudentProxy(JPAStudent jpaStudent) {
        this.jpaStudent = jpaStudent;
    }

    @Autowired
    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public JPAStudent getJPAObject() {
        return jpaStudent;
    }

    @Override
    public String getName() {
        return jpaStudent.getName();
    }

    @Override
    public List<Assignment> getAssignments() {
        return jpaStudent.getAssignments().stream()
                .map(this::getAssignmentJPAForm)
                .collect(Collectors.toList());
    }

    public Assignment getAssignmentJPAForm(JPAAssignment jpaAssignment) {
        return proxyFactory.create(AssignmentProxy.class, jpaAssignment);
    }

    @Override
    public void updateName(String name) {
        jpaStudent.setName(name);
    }

    @Override
    public void updateAssignments(List<Assignment> assignments) {
        List<JPAAssignment> jpaAssignments = assignments.stream()
                .map(assignment -> ((AssignmentProxy) assignment).getJPAObject())
                .collect(Collectors.toList());

        jpaStudent.setAssignments(jpaAssignments);
    }

    @Override
    public double getAvgTime() {
        List<JPAAssignment> jpaAssignments = jpaStudent.getAssignments();
        double totalTimeSpent = 0;
        double numberOfQuestions = 0;
        for (JPAAssignment jpaAssignment : jpaAssignments) {
            for (JPAQuestion question : jpaAssignment.getQuestions()) {
                totalTimeSpent = question.getTimeSpentMints() + totalTimeSpent;
                numberOfQuestions++;
            }
        }
        if (numberOfQuestions > 0) {
            return totalTimeSpent / numberOfQuestions;
        } else {
            return 0;
        }
    }


    @Override
    public Map<String, Double> getCorrectIncorrectAnswers() {
        List<JPAAssignment> jpaAssignments = jpaStudent.getAssignments();
        //Partial correct treated as 0.5 correct
        double correct = 0;
        double incorrect = 0;
        for (JPAAssignment jpaAssignment : jpaAssignments) {
            for (JPAQuestion question : jpaAssignment.getQuestions()) {
                switch (question.getResult()) {
                    case WRONG:
                        incorrect = incorrect + 1;
                        break;
                    case PARTIAL:
                        correct = correct + 0.5;
                        break;
                    case RIGHT:
                        correct = correct + 1;
                        break;
                    default:
                        break;
                }
            }
        }
        Map<String, Double> map = new HashMap<>();
        map.put("correct", correct);
        map.put("incorrect", incorrect);
        return map;
    }
}
