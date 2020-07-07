package com.sasadara.gradingapplication.dbadapterjpa.proxy;

public interface Proxy<T extends Object> {
    T getJPAObject();
}
