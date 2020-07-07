package com.sasadara.gradingapplication.dbadapterjpa;


import com.sasadara.gradingapplication.ports.secondary.datastore.TransactionalRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class SpringTransactionalRunner implements TransactionalRunner {
    @Override
    public void executeInTransaction(Runnable runnable) {
        runnable.run();
    }
}
