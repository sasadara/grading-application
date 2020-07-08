package com.sasadara.gradingapplication.ports.secondary.datastore;

public interface TransactionalRunner {
    void executeInTransaction(Runnable runnable);
}
