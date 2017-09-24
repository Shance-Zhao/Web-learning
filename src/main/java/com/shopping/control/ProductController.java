package com.shopping.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.dao.ProductDAO;
import com.shopping.pojo.Product;



@Controller
@RequestMapping("/product/*")
public class ProductController {
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@RequestMapping(value="/deletesuccess", method=RequestMethod.POST)
	protected ModelAndView manage(HttpServletRequest request) throws Exception {
		System.out.println("pppppppppppppp");
		int proId=Integer.parseInt(request.getParameter("proID"));
		productDao.delete(proId);
		List<Product>products = productDao.showAll();
		request.getSession().setAttribute("products",products);
		return new ModelAndView("admin-home");

	}
}
