//package com.shopping.validator;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//import com.shopping.pojo.Cart;
//
//public class CartValidator  implements Validator {
//	public boolean supports(Class aClass) {
//		return Cart.class.equals(aClass);
//	}
//
//	public void validate(Object obj, Errors errors) {
//		Cart newCart = (Cart) obj;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemQuantity", "error.invalid.Cart", "itemQuantity Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemAllSale", "error.invalid.Cart", "itemAllSale Required");
//	}
//}
