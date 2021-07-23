package com.soho.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/*
 * BeanPostProcessor: Bean后置处理器
 * 应用场景：
 */
public class MyBeanPostProcessor implements BeanPostProcessor{
	public MyBeanPostProcessor() {
		super();
		System.out.println("这是BeanPostProcessor的实现类MyBeanPostProcessor实例化.");
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		System.out.println("MyBeanPostProcessor调用postProcessBeforeInitialization对属性进行更改！beanName="+beanName);
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("MyBeanPostProcessor调用postProcessAfterInitialization对属性进行更改！beanName="+beanName);
	    return bean;
	}
}
