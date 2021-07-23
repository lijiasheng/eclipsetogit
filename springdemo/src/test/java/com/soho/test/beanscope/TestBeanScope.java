package com.soho.test.beanscope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.soho.beanscope.BeanScope;
import com.soho.ioc.JuiceMaker;
import com.soho.ioc.Source;

import org.junit.*;


public class TestBeanScope 
{
   @Test
	public void test() {
	   
	   ApplicationContext context = new ClassPathXmlApplicationContext(
               new String[]{"spring-beanscope.xml"}
       );

       BeanScope beanScope = (BeanScope) context.getBean("beanScope");
      
       beanScope.say();
   }
   
}
