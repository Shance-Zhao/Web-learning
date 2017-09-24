package com.shopping.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shopping.pojo.Customer;

@Component

public class CustomerValidator implements Validator {

//	@Autowired
//	@Qualifier("customerDao")
//	CustomerDAO customerDAO;
	public boolean supports(Class aClass) {
		return Customer.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Customer newCustomer = (Customer) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.customer", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.customer", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.customer", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerPassword", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerEmail", "error.invalid.customer","Email Required");
		
		
		// check if customer exists

//		try {
//			Customer cus = customerDAO.getCustomerEmail(newCustomer.getUserEmail());
//			if(cus !=null)
//				errors.rejectValue("customerEmail", "error.invalid.customer", "Customer Email already Exists");
//		} catch(CustomerException e) {
//			System.err.println("Exception in Customer validator");
//		}
//	
	}
}
