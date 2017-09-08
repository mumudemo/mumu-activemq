package com.lovecws.mumu.activemq.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;
/**
 * activeMQ消息发送
 * @author lovercws
 * @version 2016年5月20日 下午3:19:19
 */
@Component
public class MessageSender {
	
	/**消息队列**/
	private String queueName;//队列名称
	public String getQueueName() {
		return queueName;
	}
	public MessageSender setQueueName(String queueName) {
		this.queueName = queueName;
		return this;
	}
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate queueJmsTemplate;
	
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param message 消息内容
	 */
	public void sendQueueMessage(final Serializable message) {
		sendQueueMessage(queueName, message);
	}
	/**
	 * 发送一条消息到指定的队列（目标）
	 * @param queueName 主题名称
	 * @param message 消息内容
	 */
	public void sendQueueMessage(String queueName,final Serializable message) {
		queueJmsTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
	}
	
	
	/**activeMQ消息主题**/
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate topicJmsTemplate;
	
	private String topicName;//主题名称
	public String getTopicName() {
		return topicName;
	}
	public MessageSender setTopicName(String topicName) {
		this.topicName = topicName;
		return this;
	}
	/**
	 * 发送一条消息到指定的订阅者（目标）
	 * @param message 消息内容
	 */
	public void sendTopicMessage(final Serializable message) {
		sendTopicMessage(topicName, message);
	}
	/**
	 * 发送消息到指定的订阅者（目标）
	 * @param topicName 主题名称
	 * @param message 消息内容
	 */
	public void sendTopicMessage(String topicName,final Serializable message) {
		topicJmsTemplate.send(topicName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
	}
}
