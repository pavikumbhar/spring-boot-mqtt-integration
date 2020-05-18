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
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
				mqttProp.getClientId() + new Random().nextInt(), mqttPahoClientFactory, mqttProp.getMailSubscriptionTopic());
		adapter.setCompletionTimeout(mqttProp.getConnectionTimeout());
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setOutputChannel(mqttMailInputChannel());
		return adapter;
	}
	
	@Bean
	public MessageChannel mqttCommentInputChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageProducer commentMessageProducer() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
				mqttProp.getClientId() + new Random().nextInt(), mqttPahoClientFactory, mqttProp.getCommentSubscriptionTopic());
		adapter.setCompletionTimeout(mqttProp.getConnectionTimeout());
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setOutputChannel(mqttCommentInputChannel());
		return adapter;
	}
	

}
