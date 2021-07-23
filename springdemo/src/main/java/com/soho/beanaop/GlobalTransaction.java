package com.soho.beanaop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface GlobalTransaction {
	
	//全局交易名称
	String name() default "" ;
	
	//超时时间
	int timeoutMills() default 60000;
}
