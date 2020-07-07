package com.sasadara.grandingapplication.dbadapterjpa.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoBindProxy {
	/**
	 * binded class
	 */
	Class<?> value();
}
