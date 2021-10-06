package com.training.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.training.bean.FailedProduct;

@EnableJms
@Configuration
public class ActiveMqConfiguration {

	@Value("${spring.activemq.broker-url}")
	private String mqUrl;

	@Value("${myqueue}")
	private String queue;

	@Bean
	public ActiveMQQueue getMQ() {
		return new ActiveMQQueue(queue);
	}

	@Bean
	public ActiveMQConnectionFactory getMqFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(mqUrl);
		return factory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(getMqFactory());
		template.setMessageConverter(jacksonJmsMessageConverter());
		template.setPubSubDomain(true);
		return template;
	}
	
	// Serialize message content to json using TextMessage public
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
}
