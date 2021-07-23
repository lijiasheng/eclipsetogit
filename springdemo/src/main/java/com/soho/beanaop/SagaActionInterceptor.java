package com.soho.beanaop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SagaActionInterceptor implements MethodInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SagaActionInterceptor.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("enter SagaActionInterceptor invoke...");
		Method method = invocation.getMethod();
		SagaAction annotation = getAnnotation( method , SagaAction.class );
		
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
