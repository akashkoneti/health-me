package com.tarp.healthme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; //automatically creates objects.
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetailsService; //spring security service for user details.

import com.tarp.healthme.dto.UserRegistrationDto;
import com.tarp.healthme.entity.User;
import com.tarp.healthme.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User save(UserRegistrationDto registration) {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setRole("DOCTOR");
		return userRepository.save(user);
	}

	//predefined function of spring security user details.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
	}

	public List<User> listAll() {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByDoctor(currentUser);
	}
     
    public void saveClient(User user) {
    	userRepository.save(user);
    }
     
    public User get(long id) {
        return userRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	userRepository.deleteById(id);
    }
}