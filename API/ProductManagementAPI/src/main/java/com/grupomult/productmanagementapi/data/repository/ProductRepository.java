package com.grupomult.productmanagementapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupomult.productmanagementapi.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
