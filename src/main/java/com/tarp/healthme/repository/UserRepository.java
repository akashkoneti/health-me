package com.tarp.healthme.repository;

import org.springframework.data.jpa.repository.JpaRepository; //has operations to manage entities
import org.springframework.stereotype.Repository;

import com.tarp.healthme.entity.User;

//repository to manage users.
@Repository
public interface UserRepository extends JpaRepository < User, Long > {
	User findByEmail(String email);
}