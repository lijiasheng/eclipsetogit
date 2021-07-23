package com.soho.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
 * bean生命周期的演示
 */

public class BeanLifeCycle implements InitializingBean , DisposableBean , BeanNameAware , ApplicationContextAware , BeanFactoryAware
{
	
	private String name;
	private String mobile;
	
	private ApplicationContext applicationContext ;
	private String beanName;
	
	private BeanFactory   beanFactory ;
	
	public BeanLifeCycle() {
		super();
		System.out.println("BeanLifeCycle实例化.");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
	}
	
	public void start() {
		System.out.println("bean init-method:start");
	}
	
	public void stop() {
		System.out.println("bean destroy-method:stop");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("[属性注入] name="+name);
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		System.out.println("[属性注入] mobile="+mobile);
		this.mobile = mobile;
	}
	
	@Override
	//setBeanName实际上是获取bean定义的id属性。
	public void setBeanName(String name) {
		this.beanName = name;
		System.out.println("[接口BeanNameAware.setBeanName() ]" + this.beanName );
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		System.out.println("[接口applicationcontextaware.]"+ this.applicationContext);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		
		this.beanFactory = beanFactory ;
		
		System.out.println("[接口BeanFactoryAware.]"+ this.beanFactory);
	}
	
	
}
