package com.shopping.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//import com.shopping.dao.CartDAO;
import com.shopping.dao.CustomerDAO;
import com.shopping.dao.ProductDAO;
//import com.shopping.pojo.Cart;
import com.shopping.pojo.Customer;
import com.shopping.pojo.Product;

@Controller
@RequestMapping("/customer/*")
public class CartController {

//	@Autowired
//	@Qualifier("cartDao")
//	CartDAO cartDao;
//	
//	@Autowired
//	@Qualifier("cartValidator")
//	CartValidator cartValidator;
//	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;
	
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(cartValidator);
//	}
	
	
////	add Cart
//		@ResponseBody
//		@RequestMapping(value = "addCart", method = RequestMethod.POST)
//		public void addCart(HttpServletRequest request, HttpServletResponse response,ArrayList<Product> prolist)throws Exception{
//		//		throws CartException, IOException {
//			long productId = Integer.parseInt(request.getParameter("productId"));
//			Product p = productDao.getById(productId);
//			Customer customer = (Customer) request.getSession().getAttribute("cutomer");
//			System.out.println(customer.getPersonID());
//			Cart cart = new Cart();
//			customer.setCart(cart);
//			cartDao.addProduct(p);
//		//	int clickNum = Integer.parseInt(request.getParameter("clickNum"));
//			cartDao.addProduct(p);
//			p.setClickNum(p.getClickNum()+1);
//
//			System.out.println(p.getName());
//		//	List<Product> prolist=null;
//			//List<Product> prolist = new ArrayList<Product>();
//		//	prolist.add(p);
//			prolist.add(p);
//			request.getSession().setAttribute("prolist", prolist);
//			System.out.println(request.getSession().getAttribute("prolist"));
//			JSONObject obj =new JSONObject();
//			//obj.put(", value)
//			obj.put("prolist", prolist);
//			PrintWriter out = response.getWriter();
//			out.print(obj);
//			System.out.println(obj.length());
//		}
		
		
		
//		@RequestMapping(value="showCart", method= RequestMethod.GET)
//		protected ModelAndView showCart(HttpServletRequest request, HttpServletResponse response) throws CartException 
//		{
//			
//			
//			return null;
//		}
		@RequestMapping(value="showCart",method = RequestMethod.GET)
		protected ModelAndView showCart(HttpServletRequest request, HttpServletResponse response) throws Exception 
		{
			List<Product> prolist  = (ArrayList<Product>) request.getSession().getAttribute("prolist");
			
			System.out.println(prolist.size());
		//	Product p = productDao.getById(productId);
			return new ModelAndView("customer-cart","prolist",prolist);
		
		}
}
