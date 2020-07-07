package com.sasadara.gradingapplication.interactors.usecases;


import com.sasadara.gradingapplication.ports.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.CommandUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;

public abstract class TransactionalCommandUseCase<T extends Request> extends AbstractUseCase implements CommandUseCase<T> {
    private final TransactionalRunner transactionalRunner;

    public TransactionalCommandUseCase(TransactionalRunner transactionalRunner) {
        this.transactionalRunner = transactionalRunner;
    }

    @Override
    public final void execute(T request) {
        checkFirstExecution();
        transactionalRunner.executeInTransaction(() -> {
            executeInTransaction(request);
        });
    }

    protected abstract void executeInTransaction(T request);

}
