package com.pavikumbhar.mqtt.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavikumbhar.mqtt.gateway.MailMqttGateway;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author pavikumbhar
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/mqtt")
public class MqttController {

    @Autowired
    private MailMqttGateway mailMqttGateway;

    @PostMapping("publish")
    public void publishMessageV2(@RequestBody String message) {
        log.info("message : {}",message);
        String topic="mailFilter/mailId/"+new Random().nextInt()+"/topic";
        mailMqttGateway.sendToMqtt(topic,message);

    }

}