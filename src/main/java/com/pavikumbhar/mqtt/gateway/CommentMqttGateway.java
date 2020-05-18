package com.pavikumbhar.mqtt.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultRequestChannel = "mqttCommentOutboundChannel")
public interface CommentMqttGateway extends MqttGateway{
   
}