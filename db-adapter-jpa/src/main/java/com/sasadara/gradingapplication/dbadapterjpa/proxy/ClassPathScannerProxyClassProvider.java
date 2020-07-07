package com.sasadara.gradingapplication.dbadapterjpa.proxy;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Scans for Proxy classes under chosen package.
 * The proxies should be annotated with AutoBindProxy.
 **/
@Component
public class ClassPathScannerProxyClassProvider {
    private static final String PACKAGE_ROOT_TO_SCAN = "com.sasadara.gradingapplication.dbadapterjpa";

    private Map<Class<?>, Class<Proxy<?>>> proxyClassesByBindedClass = new HashMap<>();

    public ClassPathScannerProxyClassProvider() {
        init();
    }

    private void init() {
        scanClassPathToMap();
    }

    private void scanClassPathToMap() {
        for (Class<?> annotatedClass : getAnnotatedClasses()) {
            Class<Proxy<?>> proxyClass = asProxyClass(annotatedClass);
            Class<?> bindedClass = proxyClass.getAnnotation(AutoBindProxy.class).value();
            proxyClassesByBindedClass.put(bindedClass, proxyClass);
        }
    }

    private Set<Class<?>> getAnnotatedClasses() {
        return new Reflections(PACKAGE_ROOT_TO_SCAN).getTypesAnnotatedWith(AutoBindProxy.class);
    }

    @SuppressWarnings("unchecked")
    private Class<Proxy<?>> asProxyClass(Class<?> clazz) {
        if (!Proxy.class.isAssignableFrom(clazz)) {
            throw new AnnotatedClassDoesNotImplementsProxyInterfaceException(clazz);
        }
        return (Class<Proxy<?>>) clazz;
    }

    /**
     * not type safe. can't check proxy class's type parameter
     **/
    Class<Proxy<?>> getProxyClassFor(Class<?> targetClass) {
        checkExists(targetClass);
        return proxyClassesByBindedClass.get(targetClass);
    }

    private void checkExists(Class<?> targetClass) {
        if (!proxyClassesByBindedClass.containsKey(targetClass)) {
            throw new NoAnnotatedProxyClassFoundException(targetClass);
        }
    }

    public static class NoAnnotatedProxyClassFoundException extends RuntimeException {
        NoAnnotatedProxyClassFoundException(Class<?> targetClass) {
            super(String.format("for target class %s", targetClass));
        }
    }

    public static class AnnotatedClassDoesNotImplementsProxyInterfaceException extends RuntimeException {
        AnnotatedClassDoesNotImplementsProxyInterfaceException(Class<?> clazz) {
            super(String.format(": %s", clazz));
        }
    }

}
