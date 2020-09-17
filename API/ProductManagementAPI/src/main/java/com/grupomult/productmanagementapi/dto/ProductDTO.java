package com.grupomult.productmanagementapi.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ProductDTO {
	private static final long serialVersionUID = 1L;

	@NotNull
	private int id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String category;
	
	@NotNull
	private Double value;
	
	@NotNull
	private Date createDate;
	
	private Date updateDate;
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(int id, String name, String category, Double value, Date createDate){
		this.id = id;
		this.name = name;
		this.category = category;
		this.value = value;
		this.createDate = createDate;
	}
	
	public ProductDTO(int id, String name, String category, Double value, Date createDate, Date updateDate){
		this.id = id;
		this.name = name;
		this.category = category;
		this.value = value;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
