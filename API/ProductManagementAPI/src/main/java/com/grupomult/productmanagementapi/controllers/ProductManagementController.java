package com.grupomult.productmanagementapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupomult.productmanagementapi.dto.ProductDTO;
import com.grupomult.productmanagementapi.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductManagementController {
	
	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public String createProduct(@Valid @RequestBody ProductDTO productDto, BindingResult result) {
		if(!result.hasErrors()) {
			productService.create(productDto);
			return "Produto criado";						
		}
		
		return "Erro";
	}
	
	@GetMapping("/product/all")
	public List<ProductDTO> getAllProducts() {
	    return productService.retrieveAll();
	}
	
	@PutMapping("/product/{id}")
	public String updateProduct(@PathVariable(value = "id") Integer userId, @Valid @RequestBody ProductDTO productDto, BindingResult result) {
		if(!result.hasErrors()) {
			productService.update(userId, productDto);
			return "Sucesso ao atualizar";
		}
		
		return "Erro ao atualizar";
	}
	
	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable(value = "id") Integer userId) {
		
		if(productService.delete(userId)) {
			return "Deleção feita";
		}
		return "Erro ao deletar";
	}
}
