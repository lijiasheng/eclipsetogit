package com.soho.beanaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAopTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("现在开始初始化容器");  
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-beanaop.xml"); 
		
		UserService bean =(UserService) ctx.getBean("userService");
		
		bean.perform();
		
		bean.transferIn();
		
		System.out.println("finished.");
		
	}
}
