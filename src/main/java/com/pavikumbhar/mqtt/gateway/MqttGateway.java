package com.pavikumbhar.mqtt.gateway;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 
 * @author pavikumbhar
 *
 */
public interface MqttGateway {
    
    void sendToMqtt(String data);
    
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);
    
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
    
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, @Header(MqttHeaders.RETAINED) Boolean retained, String payload);

}