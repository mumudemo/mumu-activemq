package com.lovecws.mumu.activemq;

import com.lovecws.mumu.activemq.entity.PhoneNoticeInfo;
import com.lovecws.mumu.activemq.sender.MessageSender;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:activemq.xml");
		applicationContext.start();

		MessageSender messageSender = applicationContext.getBean(MessageSender.class);
		for (int i = 0; i < 10; i++) {
			messageSender.sendTopicMessage(new PhoneNoticeInfo("lovecws", "hahha", "cws", "15330061450"));
			messageSender.sendTopicMessage("lganTopic lovecws");
		}
		applicationContext.close();
		
	}
}
