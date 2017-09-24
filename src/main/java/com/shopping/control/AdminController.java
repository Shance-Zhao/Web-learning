package com.shopping.control;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.dao.AdminDAO;
import com.shopping.dao.ProductDAO;
import com.shopping.exception.ProductException;
import com.shopping.pojo.Admin;
import com.shopping.pojo.Product;
import com.shopping.validator.AdminValidator;
import com.shopping.validator.GeneralValidator;

import sun.misc.BASE64Decoder;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;

	@Autowired
	@Qualifier("adminValidator")
	AdminValidator validator;

	

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	
//	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
//	protected ModelAndView adminLogin() throws Exception {
//
//		return new ModelAndView("admin-login");
//
//	}
//
//	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
//	protected ModelAndView adminLoginSuccess(HttpServletRequest request) throws Exception {
//		HttpSession session = (HttpSession) request.getSession();
//		try {
//			System.out.println("loginAdmin");
//			// check if this admin exist
//			Admin adm = adminDao.getAdNandP(request.getParameter("adminName"), request.getParameter("adminPassword"));
//
//			if (adm == null) {
//				System.out.println("UserName/Password does not exist");
//				session.setAttribute("errorMessage", "UserName/Password does not exist");
//				return new ModelAndView("error");
//			}
//
//			session.setAttribute("adm", adm);
//			return new ModelAndView("admin-home", "admin", adm);
//		} catch (Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//			session.setAttribute("errorMessage", "error while login");
//			return new ModelAndView("error");
//		}
//
//	}

	
	private void askForPassword(HttpServletResponse response)
	{
		response.setStatus(response.SC_UNAUTHORIZED);//I.e.,401
		response.setHeader("WWW-Authenticate", "BASIC realm=\"FinalShop\"");
	}
	
	private boolean areEqual(String s1, String s2)
	{
	//	s1="a";
//		Session hibernatesession = sf.openSession();	
//		Query hqlquery = hibernatesession.createQuery("from Admin");
//		List<Admin> admins = hqlquery.list();
		List<Admin> admins =adminDao.show();
		for(int i=0;i<admins.size();i++){
			Admin admin = admins.get(i);
			if(admin.getAdminName().equals(s1) && admin.getAdminPassword().equals(s2))
			{
			//	s2 = (new StringBuffer(s2)).reverse().toString();
				System.out.println(s2);
				//return((s1.length()>0)&& s1.equals(s2));
				return true;
			}	
		}
		return false;
		
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public ModelAndView authentication(HttpServletRequest request,HttpServletResponse response) throws IOException {

		String authorization = request.getHeader("Authorization");

		if(authorization == null){
			askForPassword(response);
		}else{
			String adminInfo = authorization.substring(6).trim();
			BASE64Decoder decoder = new BASE64Decoder();
			String nameAndPassword = new String(decoder.decodeBuffer(adminInfo));
			
			int index = nameAndPassword.indexOf(":");
			String admin = nameAndPassword.substring(0, index);
			String password = nameAndPassword.substring(index+1);
			Admin adm = adminDao.getAdNandP(admin, password);
			request.getSession().setAttribute("adm", adm);
			if(areEqual(admin,password)){
				//response.sendRedirect("home.jsp");
				
				return new ModelAndView("admin-home", "admin", adm);
			}else{
				askForPassword(response);
			}
		}
		
		return null;
	}

	
	
	
	
	// 2.3 Logout the account, and return the main page
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected ModelAndView logoutAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);// avoid to create
														// Session
		if (session == null) {
			return new ModelAndView("admin-login");
		}
		session.removeAttribute("admin");
		System.out.print("Logout");
		//request.getSession().getAttribute("adm");
		
		return new ModelAndView("admin-login", "admin", new Admin());
	}
	
	

	// admin-home
	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	protected ModelAndView adminHome() throws Exception {

		return new ModelAndView("admin-home");
	}

	// addTable
	@RequestMapping(value = "/addTable", method = RequestMethod.GET)
	protected ModelAndView addTable() throws Exception {

		// session.removeAttribute("admin");
		return new ModelAndView("addTable");

	}
	//Manage All
	@RequestMapping(value="/manage", method=RequestMethod.GET)
	protected ModelAndView goManageTable() throws Exception {

		// session.removeAttribute("admin");
		System.out.println("manage");
		return new ModelAndView("admin-manage");

	}
	
	@RequestMapping(value="/manage", method=RequestMethod.POST)
	protected ModelAndView ManageTable() throws Exception {

		// session.removeAttribute("admin");
		return new ModelAndView("admin-manage");

	}
	
	
}
