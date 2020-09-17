package com.grupomult.productmanagementapi.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupomult.productmanagementapi.entity.Product;

public interface Products extends JpaRepository<Product, Long>{

}
