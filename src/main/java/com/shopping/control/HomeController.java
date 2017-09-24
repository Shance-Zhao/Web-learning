//package com.shopping.control;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.shopping.dao.AdminDAO;
//import com.shopping.pojo.Admin;
//import com.shopping.validator.AdminValidator;
//
//import sun.misc.BASE64Decoder;
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//@RequestMapping("/admin/*")
//public class HomeController {
//	@Autowired
//	@Qualifier("adminDao")
//	AdminDAO adminDao;
//
//	@Autowired
//	@Qualifier("adminValidator")
//	AdminValidator validator;
//	
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(validator);
//	}
//	
//	//Configuration cfg = new Configuration();
////	SessionFactory sf = cfg.configure().buildSessionFactory();
////	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	private void askForPassword(HttpServletResponse response)
//	{
//		response.setStatus(response.SC_UNAUTHORIZED);//I.e.,401
//		response.setHeader("WWW-Authenticate", "BASIC realm=\"FinalShop\"");
//	}
//	
//	private boolean areEqual(String s1, String s2)
//	{
//	//	s1="a";
////		Session hibernatesession = sf.openSession();	
////		Query hqlquery = hibernatesession.createQuery("from Admin");
////		List<Admin> admins = hqlquery.list();
//		List<Admin> admins =adminDao.show();
//		for(int i=0;i<admins.size();i++){
//			Admin admin = admins.get(i);
//			if(admin.getAdminName().equals(s1) && admin.getAdminPassword().equals(s2))
//			{
//			//	s2 = (new StringBuffer(s2)).reverse().toString();
//				System.out.println(s2);
//				//return((s1.length()>0)&& s1.equals(s2));
//				return true;
//			}	
//		}
//		return false;
//		
//	}
//	
//	/**
//	 * Simply selects the home view to render by returning its name.
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
//	public ModelAndView authentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
//
//		String authorization = request.getHeader("Authorization");
//
//		if(authorization == null){
//			askForPassword(response);
//		}else{
//			String adminInfo = authorization.substring(6).trim();
//			BASE64Decoder decoder = new BASE64Decoder();
//			String nameAndPassword = new String(decoder.decodeBuffer(adminInfo));
//			
//			int index = nameAndPassword.indexOf(":");
//			String admin = nameAndPassword.substring(0, index);
//			String password = nameAndPassword.substring(index+1);
//			Admin adm = adminDao.getAdNandP(admin, password);
//			if(areEqual(admin,password)){
//				//response.sendRedirect("home.jsp");
//				return new ModelAndView("admin-home", "admin", adm);
//			}else{
//				askForPassword(response);
//			}
//		}
//		return null;
//	}
//	
//}
