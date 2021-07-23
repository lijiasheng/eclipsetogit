package com.soho.beanlifecycle;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
	
	public MyInstantiationAwareBeanPostProcessor() {
		super();
		System.out.println("这是MyInstantiationAwareBeanPostProcessor构造器,继承InstantiationAwareBeanPostProcessorAdapter");
	}
	
	@Override
	//必须return null;不然后续的调用不执行.
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		
		System.out.println("MyInstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法[实例化Bean之前调用 ]beanname="+beanName);
		return null;
	}
	
		
//	@Override
//	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		// TODO Auto-generated method stub
//		System.out .println("MyInstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法[实例化Bean之后调用]beanname="+beanName);
//		return bean;
//	}
	
	
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		
		System.out .println("MyInstantiationAwareBeanPostProcessor调用postProcessAfterInstantiation方法[实例化Bean之后调用]beanname="+beanName);
		return super.postProcessAfterInstantiation(bean, beanName);
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
			String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("MyInstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法 pvs="+pvs+",beanName="+beanName);
		return pvs;
	}
}
