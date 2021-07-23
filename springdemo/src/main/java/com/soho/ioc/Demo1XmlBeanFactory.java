package com.soho.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo1XmlBeanFactory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("resource")
		ApplicationContext  context = new ClassPathXmlApplicationContext ("classpath:applicationContext.xml");

		 JuiceMaker maker = context.getBean(JuiceMaker.class);
		 maker.makeJuice();
	}

}
