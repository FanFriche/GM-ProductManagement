package com.grupomult.productmanagementapi.controllers.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.grupomult.productmanagementapi.controllers.dto.ProductDTO;
import com.grupomult.productmanagementapi.controllers.dto.ProductMetadataDTO;
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
	
	//TODO REMOVE
    public List<Product> getPostsList(int page, int size) {
        Pageable pageReq = PageRequest.of(page, size, Sort.by(Order.asc("name")));
        
        Page<Product> posts = productRepository.findAll(pageReq);
        
        return posts.getContent();
    }
	
	public List<ProductDTO> retrieveAll(int page, int size) {
        //Pageable pageable = PageRequest.of(page, size, Sort.by(Order.asc("name")));
		Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        
        return products.getContent().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	public ProductMetadataDTO getProductsPaginatorMetadata(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productsPaginadorMetadata = productRepository.findAll(pageable);
		
		ProductMetadataDTO productMetadataDTO = new ProductMetadataDTO();
		productMetadataDTO.setTotalNumberOfPages(productsPaginadorMetadata.getTotalElements());
		
		return productMetadataDTO;
		
	}
	
	public List<ProductDTO> retrieveAllByFilter(String nomeProduto, String categoria) {
		List<Product> products = productRepository.findByName(nomeProduto, categoria);
		
		return products.stream().map(this::convertToDto).collect(Collectors.toList());
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
	
	private ProductDTO convertToDto(Product product) {
		ProductDTO productDto = new ProductDTO(product);
        return productDto;
    }
}
