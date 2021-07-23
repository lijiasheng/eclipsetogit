package com.soho.beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("现在开始初始化容器");  
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-lifecycle.xml"); 
		System.out.println("容器初始化成功"); 
		
		System.out.println("容器getBean");
		BeanLifeCycle bean =(BeanLifeCycle) ctx.getBean("beanLifeCycle");
		System.out.println("mobile =  " + bean.getMobile() ); 
		
		System.out.println("finished.");
		
	}
}
