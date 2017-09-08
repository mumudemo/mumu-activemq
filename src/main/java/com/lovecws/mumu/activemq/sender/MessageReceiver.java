package com.lovecws.mumu.activemq.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;

/**
 * activeMQ消息接受
 * @author lovecws
 */

public class MessageReceiver {
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate queueJmsTemplate;
	
	public void receiveQueueMessage(){
		Message message = queueJmsTemplate.receive();
		System.out.println(message);
	}
}
