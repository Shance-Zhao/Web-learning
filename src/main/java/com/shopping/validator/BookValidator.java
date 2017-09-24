package com.shopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shopping.pojo.Book;

public class BookValidator implements Validator {
	public boolean supports(Class aClass) {
		return Book.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Book newBook = (Book) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.book", "Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "error.invalid.book", "ISBN Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "error.invalid.book", "Author Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "img", "error.invalid.book", "Picture Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "error.invalid.book", "Quantity Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.book", "Price Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "saleprice", "error.invalid.book","Sale Price Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.book","Description Required");
	}
}
