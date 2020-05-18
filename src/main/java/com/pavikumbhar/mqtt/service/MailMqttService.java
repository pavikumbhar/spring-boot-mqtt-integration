package com.pavikumbhar.mqtt.service;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import com.pavikumbhar.mqtt.configuration.MqttSubcriberConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MessageEndpoint
public class MailMqttService {
	
	
	 @ServiceActivator(inputChannel = MqttSubcriberConfig.MQTT_MAIL_INPUT_CHANNEL)
    public void process(Message<String> message) {
		
          log.info("Recvied Topic : {}" ,message.getHeaders().get("mqtt_receivedTopic"));
          log.info("Recvied message Payload : {}" ,message.getPayload());
   
      
        
    }

}