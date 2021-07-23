package com.soho.queue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MqTestDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("resource")
		
		ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("classpath:spring-amq.xml") ;
		System.out.println("spring 容器已启动。。。");
		
		MqQueueProducer producer = applicationContext.getBean(MqQueueProducer.class) ;
		
		for ( int i =0 ; i < 10000 ; i ++) {
			producer.sendMsg("myQueueA", "Mq消息A" + i);
		}
	}
}
