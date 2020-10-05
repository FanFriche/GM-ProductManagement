package com.grupomult.productmanagementapi.controllers.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.grupomult.productmanagementapi.controllers.dto.ProductDTO;
import com.grupomult.productmanagementapi.controllers.dto.UpdateProductDTO;
import com.grupomult.productmanagementapi.data.entity.Product;
import com.grupomult.productmanagementapi.data.repository.ProductRepository;

@ManagedBean
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public ProductDTO create(ProductDTO productDto) {
		Product product = new Product(productDto);
		Product createdProduct = productRepository.save(product);
		
		return new ProductDTO(createdProduct);
	}
	
	public List<ProductDTO> retrieveAll() {
		List<Product> productsList = productRepository.findAll();
		
		List<ProductDTO> productsDtoList = new ArrayList<ProductDTO>();
		
		//TODO trocar para stream
		for(Product product : productsList) {
			ProductDTO productDto = new ProductDTO(product.getId(), product.getName(), product.getCategory(), product.getValue());
			productsDtoList.add(productDto);
		}
		
		return productsDtoList;
	}
	
	public ProductDTO retrieveById(Integer id) {
		return new ProductDTO(productRepository.getOne(id));
	}
	
	public ProductDTO update(Integer productId, UpdateProductDTO productDto) {
		Product product = productRepository.findById(productId).get();
		product.setCategory(productDto.getCategory());
		product.setValue(productDto.getValue());
		
		return new ProductDTO(productRepository.save(product));
	}
	
	public ProductDTO delete(Integer productId) {
		Product product = productRepository.getOne(productId);
		productRepository.deleteById(productId);
		
		return new ProductDTO(product);
	}
}
