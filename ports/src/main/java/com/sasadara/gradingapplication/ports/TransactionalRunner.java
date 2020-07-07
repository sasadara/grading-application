package com.sasadara.gradingapplication.ports;

public interface TransactionalRunner {
    void executeInTransaction(Runnable runnable);
}
