package com.soho.beanannotation.multibean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BeanInvoker {
	/*
	 * 由框架注入值
	 * value = bean class object.
	 */
	@Autowired
	private List<BeanInterface> list; 
	
	/*
	 * 由框架注入值. 
	 * key= beanName
	 * value= bean class object.
	 */
	@Autowired
	private Map<String,BeanInterface> map; 
	
	/*
	 * 由于BeanInterface有多个实例，装配会出错。
	 */
	@Autowired
	@Qualifier("beanImplTwo")
	private BeanInterface beanInterface;

	
	public void say() {
		if( null != list && 0 != list.size()) {
			System.out.println("显示list的内容");
			
			for(BeanInterface bean : list) {
				System.out.println(bean.getClass().getName());
			}
		} else {
			System.out.println("List<BeanInterface> list is null !!!!!!!!!!");
		}
		
		System.out.println("==========================================");
		
		if( null != map && 0 != map.size()) {
			for ( Map.Entry<String, BeanInterface> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "      " + entry.getValue().getClass().getName());
			}
		} else {
			System.out.println("Map<String, BeanInterface> map is null !!!!!!!!!!");
		}

		System.out.println("==========================================");
		if (null != beanInterface) {
			System.out.println(beanInterface.getClass().getName());
		} else {
			System.out.println("beanInterface is null...");
		}

		
		
	}
}
