package com.sasadara.gradingapplication.interactors.usecases.assignment;


import com.sasadara.gradingapplication.entities.assignment.Assignment;
import com.sasadara.gradingapplication.interactors.usecases.TransactionalCommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.request.assignment.AddAssignmentRequest;
import com.sasadara.gradingapplication.ports.secondary.datastore.EntityFactory;
import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.secondary.datastore.assignment.AssignmentGateway;
import com.sasadara.gradingapplication.ports.secondary.datastore.student.StudentGateway;

public class AddAssignmentUseCase extends TransactionalCommandUseCase<AddAssignmentRequest> {
    private AssignmentGateway assignmentGateway;
    private StudentGateway studentGateway;
    private EntityFactory entityFactory;

    public AddAssignmentUseCase(AssignmentGateway assignmentGateway, StudentGateway studentGateway,
                                EntityFactory entityFactory, TransactionalRunner transactionalRunner) {
        super(transactionalRunner);
        this.assignmentGateway = assignmentGateway;
        this.studentGateway = studentGateway;
        this.entityFactory = entityFactory;
    }

    @Override
    protected void executeInTransaction(AddAssignmentRequest request) {

        Assignment assignment = entityFactory.assignment();
        assignment.updateName(request.getName());

        if (!studentGateway.findById(request.getStudent()).isPresent()) {
            throw new EntityNotFoundException(
                    String.format("Student No : %s is not exist. Cannot create Assignment without Student",
                            request.getStudent()));

        }
        assignment.updateStudent(studentGateway.findById(request.getStudent()).get());
        assignmentGateway.addNew(assignment);

    }
}
