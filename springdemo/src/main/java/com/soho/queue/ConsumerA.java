package com.soho.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class ConsumerA implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			String text =(( TextMessage )message).getText();
			System.out.println(this.getClass().getSimpleName()+"接受到消息-->" + text );
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
