package com.pavikumbhar.mqtt.configuration;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
/**
 * 
 * @author pavikumbhar
 *
 */
@Configuration
public class MqttPublisherConfig {
	
	public static final String MQTT_MAIL_OUTBOUND_CHANNEL = "mqttMailOutboundChannel";
	public static final String MQTT_COMMENT_OUTBOUND_CHANNEL = "mqttCommentOutboundChannel";
	
	@Autowired
	private MqttProperties mqttProp;

	@Autowired
	private MqttPahoClientFactory mqttPahoClientFactory;
	
	@Bean
    public MessageChannel mqttMailOutboundChannel() {
        return new DirectChannel();
    }
    
   
    @Bean
    @ServiceActivator(inputChannel = MQTT_MAIL_OUTBOUND_CHANNEL)
    public MessageHandler mqttMailOutputHandler() {
        MqttPahoMessageHandler mqttMailOutputHandler = new MqttPahoMessageHandler(mqttProp.getClientId() + new Random().nextInt(), mqttPahoClientFactory);
        mqttMailOutputHandler.setAsync(true);
        mqttMailOutputHandler.setDefaultTopic(mqttProp.getMailTopic());
        return mqttMailOutputHandler;
    }
    
    @Bean
    public MessageChannel mqttCommentOutboundChannel() {
        return new DirectChannel();
    }
    
    @Bean
    @ServiceActivator(inputChannel = MQTT_COMMENT_OUTBOUND_CHANNEL)
    public MessageHandler commentMailOutputHandler() {
        MqttPahoMessageHandler mqttMailOutputHandler = new MqttPahoMessageHandler(mqttProp.getClientId() + new Random().nextInt(), mqttPahoClientFactory);
        mqttMailOutputHandler.setAsync(true);
        mqttMailOutputHandler.setDefaultTopic(mqttProp.getCommentTopic());
        return mqttMailOutputHandler;
    }
    
    
    
  
}
