package com.tarp.healthme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarp.healthme.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByCreatedBy(Long id);
	 
}
