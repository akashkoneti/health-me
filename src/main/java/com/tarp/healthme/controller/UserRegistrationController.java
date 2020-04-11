package com.tarp.healthme.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarp.healthme.dto.UserRegistrationDto;
import com.tarp.healthme.entity.User;
import com.tarp.healthme.service.UserService;
import com.tarp.healthme.validator.UserValidator;


@Controller
@RequestMapping("/register")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		return "register";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
			BindingResult result) {
		
		userValidator.validate(userDto, result);

		if (result.hasErrors()) {
			return "register";
		}

		userService.save(userDto);
		return "redirect:/register?success";
	}
}