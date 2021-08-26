package com.soho.test.beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.soho.beanscope.BeanScope;

public class App {
	public static  void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext  context = new ClassPathXmlApplicationContext("spring-beanscope.xml") ;
		
		BeanScope obj = ( BeanScope )context.getBean("beanScope");
		
		obj.say();
		
	}
	
	
	
}