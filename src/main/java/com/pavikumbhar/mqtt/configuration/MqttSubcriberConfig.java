package com.pavikumbhar.mqtt.configuration;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttSubcriberConfig {

	@Autowired
	private MqttProperties mqttProp;

	@Autowired
	private MqttPahoClientFactory mqttPahoClientFactory;

	@Bean
	public MessageChannel mailInputChannel() {
		return new DirectChannel();
	}

	
	
	@Bean
	public MessageProducer mailMessageProducer() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
				mqttProp.getClientId() + "_input", mqttPahoClientFactory, mqttProp.getMailSubscriptionTopic());
		adapter.setCompletionTimeout(mqttProp.getConnectionTimeout());
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setOutputChannel(mailInputChannel());
		return adapter;
	}
	
	
	
	
	
    @Bean
    @ServiceActivator(inputChannel = "mailInputChannel")
    public MessageHandler handler() {
        return message -> {
        	System.out.println( MqttAsyncClient.generateClientId());
        	  System.out.println(String.format("[%s] %s", message.getHeaders().get("mqtt_receivedTopic"), message.getPayload()));
            System.out.println("############################################"+message.getHeaders().get("mqtt_receivedTopic")+"  "+ message.getPayload());
        
        };
    }
    

}
