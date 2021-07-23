package com.soho.beanaop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GlobalTranactionInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("enter GlobalTranactionInterceptor invoke...");
		Method method = invocation.getMethod();
		GlobalTransaction annotation = getAnnotation( method , GlobalTransaction.class );
		
		if( annotation == null ) {
			return invocation.proceed() ;
		}
		
		System.out.println("method " + invocation.getMethod() + " is called on " + invocation.getThis() + " with args" +
                " " + invocation.getArguments());
        Object proceed = invocation.proceed();
        System.out.println("method " + invocation.getMethod() + " returns " + proceed);
        return proceed;
	}
	
	private <T extends Annotation > T  getAnnotation( Method method , Class<T> classz) {
		return ( method == null ? null : method.getAnnotation(classz) );
	}
}
