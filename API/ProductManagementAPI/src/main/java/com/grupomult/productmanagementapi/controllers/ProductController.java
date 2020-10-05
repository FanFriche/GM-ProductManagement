package com.grupomult.productmanagementapi.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.grupomult.productmanagementapi.controllers.dto.ProductDTO;
import com.grupomult.productmanagementapi.controllers.dto.UpdateProductDTO;
import com.grupomult.productmanagementapi.controllers.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping("/")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDto, UriComponentsBuilder uriBuilder) {
		ProductDTO productDtoResp = productService.create(productDto);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(productDtoResp.getId()).toUri();
		return ResponseEntity.created(uri).body(productDtoResp);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = productService.retrieveAll();
		
		if(products.size() > 0) {
			return ResponseEntity.ok(products);
		}
		
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable(value = "id") Integer productId) {
		return ResponseEntity.ok(productService.retrieveById(productId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable(value = "id") Integer productId, @Valid @RequestBody UpdateProductDTO updateProductDto) {
		return ResponseEntity.ok(productService.update(productId, updateProductDto));
	}
	
	@DeleteMapping("/{id}")
	public ProductDTO deleteProduct(@PathVariable(value = "id") Integer productId) {
		return productService.delete(productId);
	}
}
