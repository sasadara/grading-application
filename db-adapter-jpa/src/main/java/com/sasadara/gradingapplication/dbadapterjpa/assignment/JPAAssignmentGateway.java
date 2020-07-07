package com.sasadara.gradingapplication.dbadapterjpa.assignment;

import com.sasadara.gradingapplication.dbadapterjpa.proxy.ProxyFactory;
import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPAAssignmentGateway implements AssignmentGateway {
    private JPAAssignmentRepository jpaAssignmentRepository;
    private ProxyFactory proxyFactory;

    @Autowired
    public JPAAssignmentGateway(JPAAssignmentRepository jpaAssignmentRepository, ProxyFactory proxyFactory) {
        this.jpaAssignmentRepository = jpaAssignmentRepository;
        this.proxyFactory = proxyFactory;
    }


    @Override
    public void addNew(Assignment assignment) {
        AssignmentProxy assignmentProxy = (AssignmentProxy) assignment;
        JPAAssignment jpaAssignment = assignmentProxy.getJPAObject();
        jpaAssignmentRepository.save(jpaAssignment);
    }

    @Override
    public Optional<Assignment> findById(Long id) {
        Optional<JPAAssignment> jpaAssignment = jpaAssignmentRepository.findById(id);
        return jpaAssignment.map(this::createProxy);
    }

    @Override
    public Optional<Assignment> findByName(String name) {
        Optional<JPAAssignment> jpaAssignment = jpaAssignmentRepository.findByName(name);
        return jpaAssignment.map(this::createProxy);
    }

    @Override
    public List<Assignment> findAll() {
        return proxyAll(jpaAssignmentRepository.findAll());
    }

    private Assignment createProxy(JPAAssignment jpaAssignment) {
        return proxyFactory.create(AssignmentProxy.class, jpaAssignment);
    }

    private List<Assignment> proxyAll(List<JPAAssignment> jpaAssignments) {
        return jpaAssignments.stream()
                .map(this::createProxy)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAll(List<Assignment> assignments) {
        for (Assignment assignment : assignments) {
            delete(assignment);
        }
    }

    @Override
    public void delete(Assignment assignment) {
        JPAAssignment jpaAssignment = ((AssignmentProxy) assignment).getJPAObject();
        jpaAssignmentRepository.delete(jpaAssignment);
    }
}
