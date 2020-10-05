package com.grupomult.productmanagementapi.controllers.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.grupomult.productmanagementapi.data.entity.Product;

public class ProductDTO {
	
	private Integer id;
	
	@NotNull @NotEmpty @Length(max = 100)
	private String name;
	
	//TODO Trocar para enum
	@NotNull @NotEmpty
	private String category;
	
	//TODO validar double
	@NotNull
	private Double value;
	
	public ProductDTO() {
	}
	
	public ProductDTO(Product product){
		this.id = product.getId();
		this.name = product.getName();
		this.category = product.getCategory();
		this.value = product.getValue();
	}
	
	public ProductDTO(int id, String name, String category, Double value){
		this.id = id;
		this.name = name;
		this.category = category;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
}
