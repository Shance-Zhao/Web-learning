package com.shopping.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.dao.ProductDAO;
import com.shopping.pojo.Product;

@Controller
@RequestMapping("/*")
public class PageController {
	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	protected ModelAndView goToUserHome(HttpServletRequest request) throws Exception {
		List<Product>products = productDao.showAll();
		HttpSession session = (HttpSession) request.getSession();
		session.setAttribute("products", products);
		return new ModelAndView("/index","products",products);	
	}

}
