package com.soho.tasks;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaskTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("[Dev Branch]Begin to create spring container.....");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-tasks.xml");  
        
		System.getProperty("user.name") ;
		
		System.out.println("Task testing finished....");
		
		System.out.println("[Dev Branch] Add texts...");
		
	}

}
