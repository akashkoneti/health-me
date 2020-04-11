package com.tarp.healthme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarp.healthme.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	 
}
