package com.lovecws.mumu.activemq.listener;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;

/**
 * 监听队列
 * @author ShenHuaJie
 * @version 2016年6月14日 上午11:00:53
 */
public class MyMessageListener implements MessageListener {
	private final Logger logger = Logger.getLogger(MyMessageListener.class);

	public void onMessage(Message message) {
		try {
			Serializable object = ((ObjectMessage) message).getObject();
			logger.info(object);
		} catch (JMSException e) {
			logger.error(e);
		}
	}
}
