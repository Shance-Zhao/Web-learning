package com.shopping.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shopping.pojo.Admin;

@Component

public class AdminValidator implements Validator{
	public boolean supports(Class aClass) {
		return Admin.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Admin newAdmin = (Admin) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adminName", "error.invalid.admin", "adminName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.admin", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.admin", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adminPassword", "error.invalid.password", "Password Required");
	}
}
