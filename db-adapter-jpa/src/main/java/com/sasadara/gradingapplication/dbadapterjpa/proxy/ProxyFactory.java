package com.sasadara.gradingapplication.dbadapterjpa.proxy;

/*
 * Generic type parameter names named according to https://docs.oracle.com/javase/tutorial/java/generics/types.html
 * */
public interface ProxyFactory {
    <P extends Proxy<S>, S> P create(Class<P> proxyClass, S jpaEntity);
}