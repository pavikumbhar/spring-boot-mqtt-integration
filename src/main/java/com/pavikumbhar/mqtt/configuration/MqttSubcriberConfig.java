package com.pavikumbhar.mqtt.configuration;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
/**
 * 
 * @author pavikumbhar
 *
 */
@Configuration
public class MqttSubcriberConfig {

	public static final String MQTT_MAIL_INPUT_CHANNEL = "mqttMailInputChannel";
	public static final String MQTT_COMMENT_INPUT_CHANNEL = "mqttCommentInputChannel";
	
	@Autowired
	private MqttProperties mqttProp;

	@Autowired
	private MqttPahoClientFactory mqttPahoClientFactory;

	@Bean
	public MessageChannel mqttMailInputChannel() {
		return new DirectChannel();
	}

	
	
	@Bean
	public MessageProducer mailMessageProducer() {
		MqttPahoMessageDrivenChannelAdapter mailMessageProducer = new MqttPahoMessageDrivenChannelAdapter(
				mqttProp.getClientId() + new Random().nextInt(), mqttPahoClientFactory, mqttProp.getMailSubscriptionTopic());
		mailMessageProducer.setCompletionTimeout(mqttProp.getConnectionTimeout());
		mailMessageProducer.setConverter(new DefaultPahoMessageConverter());
		mailMessageProducer.setOutputChannel(mqttMailInputChannel());
		return mailMessageProducer;
	}
	
	@Bean
	public MessageChannel mqttCommentInputChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageProducer commentMessageProducer() {
		MqttPahoMessageDrivenChannelAdapter commentMessageProducer = new MqttPahoMessageDrivenChannelAdapter(
				mqttProp.getClientId() + new Random().nextInt(), mqttPahoClientFactory, mqttProp.getCommentSubscriptionTopic());
		commentMessageProducer.setCompletionTimeout(mqttProp.getConnectionTimeout());
		commentMessageProducer.setConverter(new DefaultPahoMessageConverter());
		commentMessageProducer.setOutputChannel(mqttCommentInputChannel());
		return commentMessageProducer;
	}
	

}
