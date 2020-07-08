package com.sasadara.gradingapplication.dbadapterjpa.proxy;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

@Component
public class AutoBindProxyFactory implements ProxyFactory {

    private final ClassPathScannerProxyClassProvider classPathScannerProxyClassProvider;
    private final AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Autowired
    public AutoBindProxyFactory(ClassPathScannerProxyClassProvider classPathScannerProxyClassProvider,
                                AutowireCapableBeanFactory autowireCapableBeanFactory) {
        this.classPathScannerProxyClassProvider = classPathScannerProxyClassProvider;
        this.autowireCapableBeanFactory = autowireCapableBeanFactory;
    }

    /**
     * proxy type is unchecked
     **/
    @Override
    @SuppressWarnings("unchecked")
    public <P extends Proxy<S>, S> P create(Class<P> proxyClass, S jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }

        return (P) createInjectedProxyFor(jpaEntity);
    }

    private Proxy<?> createInjectedProxyFor(Object jpaEntity) {
        Proxy<?> proxy = createProxyFor(jpaEntity);
        autowireCapableBeanFactory.autowireBean(proxy);
        return proxy;
    }

    private Proxy<?> createProxyFor(Object jpaEntity) {
        Class<?> jpaEntityClassType;

        //This check is performed in order to support lazy loading.
        //When lazy loading is enabled, hibernate returns sub types of our JPA class types ex: SFIObject$HibernateProxy
        //We then have an issue of finding the corresponding proxy class
        // which is annotated with @AutoBindProxy(JPAClass.class) For this reason we take the superclass type.
        if (jpaEntity instanceof HibernateProxy) {
            jpaEntityClassType = jpaEntity.getClass().getSuperclass();
        } else {
            jpaEntityClassType = jpaEntity.getClass();
        }

        Class<Proxy<?>> proxyClass = classPathScannerProxyClassProvider.getProxyClassFor(jpaEntityClassType);
        return newInstanceWithConstructorParameter(proxyClass, jpaEntity);
    }

    private static <T> T newInstanceWithConstructorParameter(Class<T> type, Object parameter) {
        try {

            //This check is performed in order to support lazy loading.
            //When lazy loading is enabled, hibernate returns sub types of our JPA class types ex: SFIObject$HibernateProxy
            //We then have an issue of finding the corresponding proxy class constructor
            //which is annotated with @AutoBindProxy(JPAClass.class) For this reason we take the superclass type.
            Class<?> paramClass;
            if (parameter instanceof HibernateProxy) {
                paramClass = parameter.getClass().getSuperclass();
            } else {
                paramClass = parameter.getClass();
            }

            Constructor<T> constructor = type.getDeclaredConstructor(paramClass);
            return constructor.newInstance(parameter);
        } catch (Exception e) {
            throw new ProxyConstructionException(e);
        }
    }
}
