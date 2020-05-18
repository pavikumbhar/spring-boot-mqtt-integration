package com.pavikumbhar.mqtt.configuration;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
/**
 * 
 * @author pavikumbhar
 *
 */
@Configuration
public class MqttConfig {

	@Autowired
	private MqttProperties mqttProp;

	@Bean
	public MqttConnectOptions mqttConnectOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName(mqttProp.getUsername());
		options.setPassword(mqttProp.getPassword().toCharArray());
		options.setServerURIs(mqttProp.getHostUrl());
		
		return options;
	}
	
	@Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectOptions());
         return factory;
    }

	

}
