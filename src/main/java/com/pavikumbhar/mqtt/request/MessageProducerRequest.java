package com.pavikumbhar.mqtt.request;

import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.support.MqttMessageConverter;
import org.springframework.messaging.MessageChannel;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author pavikumbhar
 *
 */
@Getter
@Setter
public class MessageProducerRequest {
	
	private String clientId;
	private MqttPahoClientFactory clientFactory;
	private String[] subscriptionTopic;
	private MqttMessageConverter converter;
	private MessageChannel outputChannel;
	
	public MessageProducerRequest(String clientId, MqttPahoClientFactory clientFactory, String[] subscriptionTopic) {
		super();
		this.clientId = clientId;
		this.clientFactory = clientFactory;
		this.subscriptionTopic = subscriptionTopic;
	}

}
