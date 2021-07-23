package com.soho.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/*
 * 切面类
 * <aop:aspectj-autoproxy />
 */

@Aspect
@Component
public class LoggingAspect {
	
	// *:函数返回值为任意类型；com.soho.aop..包及子包
	// *：表示包里类名， * 表示所有的类名
	// .*(..) 表示类中的方法，*表示所有的方法，（..)表示所有参数
	@Pointcut("execution(* com.soho.aop..*.*(..))") 
	public void pointCut() {}
	
	@Before(value = "pointCut()")
	public void doBefore(JoinPoint joinPoint ) {
		System.out.println("AOP Before Advice...");
	}
	
	@After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("AOP After Advice...");
    }
	
	@Around("pointCut()")
    public void around(ProceedingJoinPoint pjp){
		
		String signature; 
		Method method = (( MethodSignature) pjp.getSignature()).getMethod();
		
        System.out.println("AOP Aronud before...");
        try {
        	// getThis():返回代理对象
        	// getTarget(): 返回目标对象/被代理对象
        	// 代理对象的signature: public final void com.sun.proxy.$Proxy29.addCustomerAround(java.lang.String)
        	// 目标对象的signature: public void com.soho.aop.CustomerBoImpl.addCustomerAround(java.lang.String)
        	signature = pjp.getTarget().getClass()
        			    .getDeclaredMethod("addCustomerAround", method.getParameterTypes())
        			    .toString();
        	System.out.println("signature:" + signature);
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("AOP Aronud after...");
    }
}
