package com.pavikumbhar.mqtt.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties("mqtt")
public class MqttProperties {
    private String username;
    private String password;
    private String[] hostUrl;
    private String clientId;
    private String mailTopic;
    private Long connectionTimeout;
    private String[] mailSubscriptionTopic;
    private String[] commentSubscriptionTopic;
}
