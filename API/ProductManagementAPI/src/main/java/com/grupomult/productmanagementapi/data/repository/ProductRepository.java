package com.grupomult.productmanagementapi.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.grupomult.productmanagementapi.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer>{
	
	@Query("SELECT p FROM Product p "
		 + "WHERE (:productName IS NULL or UPPER(p.name) LIKE UPPER(CONCAT('%', :productName,'%')))"
		 + "AND"
		 + "(:categoria IS NULL or p.category = :categoria)")
    public List<Product> findByName(@Param("productName") String productName, @Param("categoria") String categoria);

}
