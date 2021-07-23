package com.soho.test.beanlifecycle;

import com.soho.test.base.UnitTestBase;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanLifeCycle extends UnitTestBase
{
   
	public TestBeanLifeCycle() {
		super("classpath:spring-lifecycle.xml");
	}
	
	@Test
	public void test() {
	   
    super.getBean("beanLifeCycle");
      
    }
   
}
