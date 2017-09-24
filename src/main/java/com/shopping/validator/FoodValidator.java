package com.shopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shopping.pojo.Food;

public class FoodValidator  implements Validator {
	public boolean supports(Class aClass) {
		return Food.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Food newFood = (Food) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.food", "Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shelflife", "error.invalid.food", "Shelf life Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "img", "error.invalid.food", "Picture Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "error.invalid.food", "Quantity Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.food", "Price Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "saleprice", "error.invalid.food","Sale Price Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.food","Description Required");
	}
}