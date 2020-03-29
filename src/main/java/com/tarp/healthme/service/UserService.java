package com.tarp.healthme.service;

import org.springframework.security.core.userdetails.UserDetailsService; //spring security service for user details.

import com.tarp.healthme.dto.UserRegistrationDto;
import com.tarp.healthme.entity.User;

//manages all the functionality for the user
public interface UserService extends UserDetailsService {

	User findByEmail(String email);

	User save(UserRegistrationDto registration);
}