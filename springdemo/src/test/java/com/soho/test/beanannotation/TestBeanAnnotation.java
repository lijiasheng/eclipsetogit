package com.soho.test.beanannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.soho.beanannotation.BeanAnnotation;
import com.soho.beanannotation.injection.service.InjectionService;
import com.soho.beanannotation.multibean.BeanInvoker;
import com.soho.beanlifecycle.BeanLifeCycle;
import com.soho.beanscope.BeanScope;
import com.soho.ioc.JuiceMaker;
import com.soho.ioc.Source;
import com.soho.test.base.UnitTestBase;

import java.io.IOException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanAnnotation extends UnitTestBase
{
   
	public TestBeanAnnotation() {
		super("classpath:spring-beanannotation.xml");
	}
	
	@Test
	public void testSay() {
		BeanAnnotation bean = super.getBean("beanAnnotation");
		bean.say("This is test.");
		
//		bean = super.getBean("bean");
//		bean.say("This is test.");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testScpoe() {
		BeanAnnotation bean = super.getBean("beanAnnotation");
		bean.myHashCode();
		
		bean = super.getBean("beanAnnotation");
		bean.myHashCode();
	}

	@Test
	public void testAutowired() {
		InjectionService service = super.getBean("injectionServiceImpl");
		service.save("This is autowired.");
	}

	@Test
	public void testMultiBean() {
		BeanInvoker invoker = super.getBean("beanInvoker");
		invoker.say();
	}
}
