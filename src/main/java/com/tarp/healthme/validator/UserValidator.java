package com.tarp.healthme.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tarp.healthme.dto.UserRegistrationDto;
import com.tarp.healthme.service.UserService;


@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserRegistrationDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserRegistrationDto user = (UserRegistrationDto) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		if (userService.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
	}
}