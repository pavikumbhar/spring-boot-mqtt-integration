package com.pavikumbhar.mqtt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
	
	private String status;
	private String message;
	

}
