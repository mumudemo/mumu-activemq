# mumu-activemq 消息中间件
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/babymm/mumu-benchmark/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/com.weibo/motan.svg?label=Maven%20Central)](https://github.com/babymm/mumu-benchmark) 
[![Build Status](https://travis-ci.org/mumudemo/mumu-activemq.svg?branch=master)](https://travis-ci.org/mumudemo/mumu-activemq)
[![OpenTracing-1.0 Badge](https://img.shields.io/badge/OpenTracing--1.0-enabled-blue.svg)](http://opentracing.io)
> ***mumu-activemq是一个对老牌mq消息中间件的学习和测试项目，本人通过这个项目来熟悉activemq的消息发送流程和消息接受流程。activemq支持各种消息协议tcp、http、udp等。activemq遵循jms开发协议，支持队列、主题两种消息模式。而且activemq已经内嵌到spring中去，当使用spring框架的时候很容易的就将activemq集成到项目中去。***

## 队列消息模式
队列一般用于点对点的消息发送和接受。消息发送者将消息发送到队列，接受者从队列中获取消息。
![队列消息模式](http://dl2.iteye.com/upload/attachment/0086/5406/847f46bb-3d45-3e6f-b663-05669360c5b1.jpg)

## 代码演示
***消息发送***
```
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
```

***消息接受***
```
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
```

## 主题消息模式
主题消息模式主要用于消息的订阅。消息发送者将消息发送到主题，然后订阅该主题的消息接受者都可以接受该消息。
![队列消息模式](http://dl2.iteye.com/upload/attachment/0086/5400/605c3d41-70af-3c3c-8f9d-0077a252964e.jpg)

## 相关阅读
[深入浅出JMS(一)–JMS基本概念](http://blog.csdn.net/jiuqiyuliang/article/details/46701559)  
[深入浅出JMS(二)–ActiveMQ简单介绍以及安装](http://blog.csdn.net/jiuqiyuliang/article/details/47160259)  
[深入浅出JMS(三)--ActiveMQ简单的HelloWorld实例](http://blog.csdn.net/jiuqiyuliang/article/details/48608237)

## 联系方式
**以上观点纯属个人看法，如有不同，欢迎指正。  
email:<babymm@aliyun.com>  
github:[https://github.com/babymm](https://github.com/babymm)**
