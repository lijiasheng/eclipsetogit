package com.soho.beanaop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface SagaAction {
	
	//bean name, must be unique
	String name() default "";
	
	String compensationMethod() default "compensation" ;
}
