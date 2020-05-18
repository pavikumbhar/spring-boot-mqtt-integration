package com.pavikumbhar.mqtt;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;
import org.springframework.integration.mqtt.event.MqttIntegrationEvent;
import org.springframework.integration.mqtt.event.MqttMessageSentEvent;
import org.springframework.stereotype.Component;
/**
 * 
 * @author pavikumbhar
 *
 */
@SpringBootApplication
@IntegrationComponentScan
public class MqttApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MqttApplication.class, args);
	}
    @Component
    public static class MqttEventListener implements ApplicationListener<MqttIntegrationEvent> {

        @Override
        public void onApplicationEvent(MqttIntegrationEvent event) {
        	
        	
            if (event instanceof MqttConnectionFailedEvent) {
                System.err.println("MQTT Error: " + event.getCause().getMessage());
                event.getCause().printStackTrace();
            }
        	
        	 if (event instanceof MqttMessageSentEvent) {
        		 System.err.println(" MqttAsyncClient.generateClientId(): " +  MqttAsyncClient.generateClientId());
        		 System.err.println("MQTT Error: " +  ((MqttMessageSentEvent)event).toString());
                 System.err.println("MQTT Error: " + event.getCause().getMessage());
                 event.getCause().printStackTrace();
             }
        }

    }
}
