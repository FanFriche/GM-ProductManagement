package com.grupomult.productmanagementapi.controllers.dto;

public class DefaultErrorDTO {
	
	private String message;
	private String error;
	
	public DefaultErrorDTO(String field, String error) {
		this.message = field;
		this.error = error;
	}
	
	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}
}
