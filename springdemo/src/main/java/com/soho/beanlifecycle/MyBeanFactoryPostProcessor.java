package com.soho.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;


//自定义BeanFactoryPostProcessor.可以获取某个bean的beandefinition信息
/*
 * BeanFactory后置处理器接口。
 * 应用场景：当Bean还未实例化之前，添加或修改bean的属性
 */

public class MyBeanFactoryPostProcessor  implements BeanFactoryPostProcessor{

	public MyBeanFactoryPostProcessor() {
		super();
		System.out.println("这是接口BeanFactoryPostProcessor的实现类MyBeanFactoryPostProcessor的构造器.");
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		System.out.println("MyBeanFactoryPostProcessor调用postProcessBeanFactory。");
		
		System.out.println("增加beanLifeCyle的属性");
		BeanDefinition bd = beanFactory.getBeanDefinition("beanLifeCycle");	
		bd.getPropertyValues().addPropertyValue("mobile", "110");
	}
}
