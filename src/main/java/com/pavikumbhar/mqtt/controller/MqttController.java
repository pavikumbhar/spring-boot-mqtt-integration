package com.pavikumbhar.mqtt.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavikumbhar.mqtt.gateway.MailMqttGateway;


@RestController
@RequestMapping(value = "/api/mqtt")
public class MqttController {

    @Autowired
    private MailMqttGateway mailMqttGateway;

    @PostMapping("publish")
    public void publishMessageV2(@RequestBody String message) {
        System.err.println(message);
        String topic="/mail/id/"+new Random().nextInt()+"/publish";
        mailMqttGateway.sendToMqtt(topic,message);

    }

}