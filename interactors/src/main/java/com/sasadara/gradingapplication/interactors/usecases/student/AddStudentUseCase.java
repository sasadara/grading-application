package com.sasadara.gradingapplication.interactors.usecases.student;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.EntityFactory;
import com.sasadara.gradingapplication.ports.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.AddStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;

import java.util.LinkedList;
import java.util.List;

public class AddStudentUseCase extends TransactionalCommandUseCase<AddStudentDetailsRequest> {
    private StudentGateway studentGateway;
    private AssignmentGateway assignmentGateway;
    private EntityFactory entityFactory;

    public AddStudentUseCase(StudentGateway studentGateway, AssignmentGateway assignmentGateway,
                             EntityFactory entityFactory, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.assignmentGateway = assignmentGateway;
        this.studentGateway = studentGateway;
        this.entityFactory = entityFactory;
    }

    @Override
    protected void executeInTransaction(AddStudentDetailsRequest request) {

        Student student = entityFactory.student();
        student.updateName(request.getName());

        List<Assignment> assignment = new LinkedList<>();
        for (Long id : request.getAssignments()) {
            if (assignmentGateway.findById(id).isPresent()) {
                assignment.add(assignmentGateway.findById(id).get());
            }
        }
        student.updateAssignments(assignment);
        studentGateway.addNew(student);

    }
}
