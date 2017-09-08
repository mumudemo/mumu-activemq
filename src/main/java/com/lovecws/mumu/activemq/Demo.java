package com.lovecws.mumu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;
import java.util.Date;

public class Demo {

	public static void main(String[] args) {
		//sendMessage();
		receiveMessage();
	}

	public static void sendMessage() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
				"failover:(tcp://192.168.11.25:61616)");
		try {
			Connection connection = activeMQConnectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("lgan");
			MessageProducer producer = session.createProducer(queue);

			TextMessage message = session.createTextMessage("lovecws"+new Date());
			producer.send(message);
			session.commit();
			connection.stop();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void receiveMessage() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
				"failover:(tcp://192.168.11.25:61616)");
		try {
			Connection connection = activeMQConnectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

			Queue queue = session.createQueue("lgan");
			MessageConsumer consumer = session.createConsumer(queue);
			Message message = consumer.receiveNoWait();
			if (message != null) {
				System.out.println(message);
				String object = ((ActiveMQTextMessage) message).getText();
				System.out.println(object);
			} else {
				System.out.println("message not found");
			}
			session.commit();
			connection.stop();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
