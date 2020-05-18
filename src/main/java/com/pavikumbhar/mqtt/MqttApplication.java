package com.pavikumbhar.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
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
  
}
