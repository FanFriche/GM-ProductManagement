package com.grupomult.productmanagementapi.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.grupomult.productmanagementapi.controllers.Products;
import com.grupomult.productmanagementapi.dto.ProductDTO;
import com.grupomult.productmanagementapi.entity.Product;

@ManagedBean
public class ProductService {
	
	@Autowired
	Products products;
	
	public ProductDTO create(ProductDTO productDto) {
		Product product = new Product(productDto.getId(), productDto.getName(), productDto.getCategory(), productDto.getValue(), productDto.getCreateDate());
		products.save(product);
		
		return productDto;
	}
	
	public List<ProductDTO> retrieveAll() {
		List<Product> productsList = products.findAll();
		
		List<ProductDTO> productsDtoList = new ArrayList<ProductDTO>();
		for(Product product : productsList) {
			ProductDTO productDto = new ProductDTO(product.getId(), product.getName(), product.getCategory(), product.getValue(), product.getCreateDate());
			productsDtoList.add(productDto);
		}
		
		return productsDtoList;
	}
	
	public ProductDTO update(Integer userId, ProductDTO productDto) {
		Product product = new Product(userId, productDto.getName(), productDto.getCategory(), productDto.getValue(), productDto.getCreateDate());
		products.save(product);
		
		return productDto;
	}
	
	public Boolean delete(Integer userId) {
		Product product = new Product();
		product.setId(userId);
		products.delete(product);
		
		return Boolean.TRUE;
	}
}
