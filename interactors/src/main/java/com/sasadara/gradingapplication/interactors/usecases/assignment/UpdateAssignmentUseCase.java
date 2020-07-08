package com.sasadara.gradingapplication.interactors.usecases.assignment;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.entities.student.Student;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.UpdateAssignmentRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;

public class UpdateAssignmentUseCase extends TransactionalCommandUseCase<UpdateAssignmentRequest> {
    private AssignmentGateway assignmentGateway;
    private StudentGateway studentGateway;

    public UpdateAssignmentUseCase(AssignmentGateway assignmentGateway,
                                   StudentGateway studentGateway,
                                   TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.assignmentGateway = assignmentGateway;
        this.studentGateway = studentGateway;
    }

    @Override
    protected void executeInTransaction(UpdateAssignmentRequest request) {
        if (assignmentGateway.findById(request.getId()).isPresent()) {
            throw new EntityNotFoundException(String.format("Assignment No : %s is not exist", request.getId()));
        }
        Assignment assignment = assignmentGateway.findById(request.getId()).get();

        if (request.getAssignmentRequest() != null && request.getAssignmentRequest().getName() != null) {
            assignment.updateName(request.getAssignmentRequest().getName());
        }
        if (request.getAssignmentRequest() != null && request.getAssignmentRequest().getStudentId() != null) {
            if (!studentGateway.findById(request.getAssignmentRequest().getStudentId()).isPresent()) {
                throw new EntityNotFoundException(String.format("Student No : %s is not exist", request.getAssignmentRequest().getStudentId()));
            }
            Student student = studentGateway.findById(request.getAssignmentRequest().getStudentId()).get();
            assignment.updateStudent(student);
            assignment.updateName(request.getAssignmentRequest().getName());
        }

    }
}
