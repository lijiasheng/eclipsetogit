package com.soho.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BeanAopTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("现在开始初始化容器");  
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-aop.xml"); 
		
		CustomerBo bean =(CustomerBo) ctx.getBean("customerBoImpl");
		
//		bean.addCustomer();
		
		bean.addCustomerAround("name");
		
		System.out.println("finished.");
	}
}
