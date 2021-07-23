package com.soho.test.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.soho.ioc.JuiceMaker;
import com.soho.ioc.Source;

import org.junit.*;


public class AppTest 
{
   @Test
	public void test() {
	   
	   ApplicationContext context = new ClassPathXmlApplicationContext(
               new String[]{"applicationContext.xml"}
       );

       Source source = (Source) context.getBean("source");
       System.out.println(source.getFruit());
       System.out.println(source.getSugar());
       System.out.println(source.getSize());
       
       
       JuiceMaker juiceMaker = (JuiceMaker) context.getBean("juiceMaker");
       System.out.println(juiceMaker.makeJuice());
	   
	   
   }
}
