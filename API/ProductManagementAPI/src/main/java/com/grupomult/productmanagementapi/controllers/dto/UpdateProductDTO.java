package com.grupomult.productmanagementapi.controllers.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class UpdateProductDTO {
	
	@NotEmpty
	private String category;
	
	private Double value;
		
	private Date updateDate = new Date();

	public String getCategory() {
		return category;
	}

	public Double getValue() {
		return value;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
}
