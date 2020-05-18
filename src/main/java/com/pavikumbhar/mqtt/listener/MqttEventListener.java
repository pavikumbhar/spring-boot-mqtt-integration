package com.pavikumbhar.mqtt.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;
import org.springframework.integration.mqtt.event.MqttIntegrationEvent;
import org.springframework.integration.mqtt.event.MqttMessageSentEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public  class MqttEventListener implements ApplicationListener<MqttIntegrationEvent> {

    @Override
    public void onApplicationEvent(MqttIntegrationEvent event) {
    	
    	
        if (event instanceof MqttConnectionFailedEvent) {
            log.error("MQTT Error: {}" ,event.getCause().getMessage());
            }
    	
    	 if (event instanceof MqttMessageSentEvent) {
    	   
    		 log.error("MQTT Error: " + event.getCause().getMessage());
            
         }
    }

}