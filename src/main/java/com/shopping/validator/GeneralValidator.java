package com.shopping.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shopping.pojo.General;


@Component
public class GeneralValidator implements Validator {
	public boolean supports(Class aClass) {
		return General.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		General newGeneral = (General) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Name", "error.invalid.general", "Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "img", "error.invalid.general", "Picture Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "error.invalid.general", "Quantity Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.general", "Price Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "saleprice", "error.invalid.general","Sale Price Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.general","Description Required");
	}
}
