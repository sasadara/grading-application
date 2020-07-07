package com.sasadara.gradingapplication.interactors.usecases;


import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import com.sasadara.gradingapplication.ports.primary.usecase.FunctionUseCase;
import com.sasadara.gradingapplication.ports.primary.usecase.request.Request;
import com.sasadara.gradingapplication.ports.primary.usecase.response.Response;

public abstract class TransactionalFunctionUseCase<T extends Request, R extends Response> extends
        AbstractUseCase implements
        FunctionUseCase<T, R> {
    private final TransactionalRunner transactionalRunner;

    public TransactionalFunctionUseCase(TransactionalRunner transactionalRunner) {
        this.transactionalRunner = transactionalRunner;
    }

    @Override
    public final R execute(T request) {
        checkFirstExecution();
        Holder<R> response = new Holder<>();

        transactionalRunner.executeInTransaction(() -> {
            R result = executeInTransaction(request);
            response.setValue(result);
        });
        return response.getValue();
    }

    protected abstract R executeInTransaction(T request);

    private static class Holder<T> {
        private T value;

        T getValue() {
            return value;
        }

        void setValue(T value) {
            this.value = value;
        }
    }

}