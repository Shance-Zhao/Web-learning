package com.shopping.control;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.shopping.dao.CartDAO;
import com.shopping.dao.CustomerDAO;
import com.shopping.dao.ProductDAO;
import com.shopping.exception.CustomerException;
//import com.shopping.pojo.Cart;
//import com.shopping.exception.CustomerException;
import com.shopping.pojo.Customer;
import com.shopping.pojo.Product;
import com.shopping.validator.CustomerValidator;

import sun.misc.BASE64Decoder;

@Controller
@RequestMapping("/customer/*")
public class CustomerController{

	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;

	@Autowired
	@Qualifier("customerValidator")
	CustomerValidator validator;
	
//	@Autowired
//	@Qualifier("bookDao")
//	BookDAO bookDao;
//	
//	@Autowired
//	@Qualifier("foodDao")
//	FoodDAO foodDao;
//	
//	@Autowired
//	@Qualifier("generalDao")
//	GeneralDAO generalDao;
	
//	@Autowired
//	@Qualifier("cartDao")
//	CartDAO cartDao;
	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	

	//login encryption
//	private void askForPassword(HttpServletResponse response)
//	{
//		response.setStatus(response.SC_UNAUTHORIZED);//I.e.,401
//		response.setHeader("WWW-Authenticate", "BASIC realm=\"final\"");
//	}
	

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	protected ModelAndView Home(HttpServletRequest request) throws Exception {
//			List<Product>products = productDao.showAll();
//			System.out.println("Products:::::::::::"+products);
//			System.out.println("Having  ------: "+products.get(1).getName());
//			return new ModelAndView("/","products",products);
//	}

	
	
	// Go to the account page, 1.login / 2.register
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	protected String goToUserHome() throws Exception {
		return ("customer-home");
	}

	// 1. login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		try {

			System.out.print("loginCustomer");
			
			BASE64Decoder decoder = new BASE64Decoder();
			String valid=new String(decoder.decodeBuffer(request.getParameter("customerPassword")));
		//	customer.setCustomerPassword(valid);
			
			Customer cus = customerDao.getNandP(request.getParameter("userName"),valid);

			if (cus == null) {
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return new ModelAndView("error");
			}

			session.setAttribute("cus", cus);
		///	PageController a= new PageController();
		//	a.goToUserHome();
			return new ModelAndView("/index", "customer", cus);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return new ModelAndView("error");
		}
	}

	//2-1. go to register page
	@RequestMapping(value = "/customer/register", method = RequestMethod.GET)
	protected ModelAndView registerCustomer() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("customer-register", "customer", new Customer());

	}
	
	//2-2. submit the information, then return the main page
	@RequestMapping(value = "/customer/register", method = RequestMethod.POST)
	protected ModelAndView registerSuccess(HttpServletRequest request,  @ModelAttribute("customer") Customer customer, BindingResult result) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		validator.validate(customer, result);
		String up=new String(decoder.decodeBuffer(customer.getCustomerPassword()));
		customer.setCustomerPassword(up);
		System.out.println(customer.getUserName()+"PPP:"+customer.getCustomerPassword());
		if (result.hasErrors()) {
			return new ModelAndView("customer-register", "customer", customer);
		}
		
		try {
			
			System.out.print("register Success");

			Customer cus = customerDao.register(customer);
			String name = cus.getUserName();
			System.out.println("User:"+cus.getUserName());
			String sendEmail = cus.getCustomerEmail();
			//request.getParameter("cus");
			request.getSession().setAttribute("customer", cus);
			
            Email sendemail = new SimpleEmail();
           
            sendemail.setHostName("smtp.gmail.com");//If a server is capable of sending email, then you don't need the authentication. In this case, an email server needs to be running on that machine. Since we are running this application on the localhost and we don't have a email server, we are simply asking gmail to relay this email.
            sendemail.setSmtpPort(587);
            sendemail.setAuthenticator(new DefaultAuthenticator("leezz569@gmail.com", "656838710Zss"));
            sendemail.setSSLOnConnect(true);
            sendemail.setFrom("leezz569@gmail.com");//This email will appear in the from field of the sending email. It doesn't have to be a real email address.This could be used for phishing/spoofing!
            sendemail.setSubject("Confirm Mail");
            sendemail.setMsg("Sign up confirmation for " + name + ". This is a confirmation email for your registration");
            sendemail.addTo(sendEmail);//Will come from the database
            sendemail.send();
	
			return new ModelAndView("/index", "customer", cus);

		} catch (CustomerException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	//2.3 Logout the account, and return the main page
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected ModelAndView logoutCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);//avoid to create Session
		if(session == null){
			return new ModelAndView("/index");
		}
		session.removeAttribute("customer");
		System.out.print("Logout");

		return new ModelAndView("/index", "customer", new Customer());

	}
	
	@RequestMapping(value="aa",method = RequestMethod.GET)
	protected String ajaxsearch() throws Exception {
		return "/search";
	}
	//generalpage
	@RequestMapping(value="generalpage",method = RequestMethod.GET)
	protected String generalpage() throws Exception {
		return "/customer-general";
	}
	
	@RequestMapping(value="all",method = RequestMethod.GET)
	protected String Allpage() throws Exception {
		return "/index";
	}

}
