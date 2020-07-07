package com.sasadara.grandingapplication.dbadapterjpa.proxy;

public interface Proxy<T extends Object> {
    T getJPAObject();
}
