package com.tarp.healthme.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tarp.healthme.entity.Food;
import com.tarp.healthme.entity.User;
import com.tarp.healthme.repository.FoodRepository;


@Service
@Transactional
public class FoodService {
 
    @Autowired
    private FoodRepository repo;
     
    public List<Food> listAll() {
    	User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repo.findByCreatedBy(currentUser.getId());
    }
     
    public void save(Food food) {
        repo.save(food);
    }
     
    public Food get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
   
}