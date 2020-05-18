package com.pavikumbhar.mqtt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttPublisherConfig {
	
	
	@Autowired
	private MqttProperties mqttProp;

	@Autowired
	private MqttPahoClientFactory mqttPahoClientFactory;
	
	@Bean
    public MessageChannel mqttMailOutboundChannel() {
        return new DirectChannel();
    }
    
   
    @Bean
    @ServiceActivator(inputChannel = "mqttMailOutboundChannel")
    public MessageHandler mqttMailOutputHandler() {
        MqttPahoMessageHandler mqttMailOutputHandler = new MqttPahoMessageHandler(mqttProp.getClientId()+"_mail", mqttPahoClientFactory);
        mqttMailOutputHandler.setAsync(true);
        mqttMailOutputHandler.setDefaultTopic(mqttProp.getMailTopic());
        return mqttMailOutputHandler;
    }
    
    
    
  
}
