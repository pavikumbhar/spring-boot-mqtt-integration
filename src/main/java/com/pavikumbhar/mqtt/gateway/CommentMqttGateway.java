package com.pavikumbhar.mqtt.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;
/**
 * 
 * @author pavikumbhar
 *
 */

import com.pavikumbhar.mqtt.configuration.MqttPublisherConfig;
@Component
@MessagingGateway(defaultRequestChannel = MqttPublisherConfig.MQTT_COMMENT_OUTBOUND_CHANNEL)
public interface CommentMqttGateway extends MqttGateway{
   
}