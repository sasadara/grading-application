package com.sasadara.gradingapplication.interactors.usecases.student;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.student.UpdateStudentDetailsRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;

import java.util.LinkedList;
import java.util.List;

public class UpdateStudentUseCase extends TransactionalCommandUseCase<UpdateStudentDetailsRequest> {
    private AssignmentGateway assignmentGateway;
    private StudentGateway studentGateway;

    public UpdateStudentUseCase(AssignmentGateway assignmentGateway, StudentGateway studentGateway,
                                TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.assignmentGateway = assignmentGateway;
        this.studentGateway = studentGateway;
    }

    @Override
    protected void executeInTransaction(UpdateStudentDetailsRequest request) {
        Student student;
        if (studentGateway.findById(request.getId()).isPresent()) {
            student = studentGateway.findById(request.getId()).get();
        } else {
            throw new EntityNotFoundException(String.format("Student No : %s is not exist", request.getId()));
        }
        List<Assignment> assignments = new LinkedList<>();
        for (Long id : request.getAssignments()) {
            if (assignmentGateway.findById(id).isPresent()) {
                assignments.add(assignmentGateway.findById(id).get());
            }
        }
        student.updateAssignments(assignments);
        studentGateway.addNew(student);

    }
}
