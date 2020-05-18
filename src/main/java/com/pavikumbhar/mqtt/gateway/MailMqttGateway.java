package com.pavikumbhar.mqtt.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultRequestChannel = "mqttMailOutboundChannel")
public interface MailMqttGateway extends MqttGateway{
   
}