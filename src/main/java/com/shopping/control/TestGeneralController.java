//package com.shopping.control;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.shopping.dao.GeneralDAO;
//import com.shopping.pojo.General;
//import com.shopping.validator.GeneralValidator;
//
//
//@Controller
//@RequestMapping("/general/*")
//public class TestGeneralController {
//	@Autowired
//	@Qualifier("generalDao")
//	GeneralDAO generalDao;
//
//	@Autowired
//	@Qualifier("generalValidator")
//	GeneralValidator generalValidator;
//
//	@Autowired
//	ServletContext servletContext;
//
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(generalValidator);
//	}
//
//
//	// go to addTable
//	@RequestMapping(value = "/addGeneral", method = RequestMethod.GET)
//	protected ModelAndView addGeneralTable() throws Exception {
//
//		return new ModelAndView("admin-addGeneral");
//	}
//
//
//	// go to success page
//	@RequestMapping(value = "/addsuccess", method = RequestMethod.POST)
//	protected ModelAndView addSuccess(HttpServletRequest request,@ModelAttribute("general")General general) throws Exception {
//
//	
//		
//			if (general.getFilename().trim() != "" || general.getFilename() != null) {
//				File directory;
//				String check = File.separator; // Checking if system is linux"/"
//												// based or windows"\" based by
//												// checking seprator used.
//				String path = null;
//				if (check.equalsIgnoreCase("\\")) {
//					path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
//					// it's calculate start with where current servlet in tomcat
//					// // so we need to replace build in the path
//					System.out.println(path);
//					System.out.println("111:" + servletContext.getRealPath(""));
//
//				}
//
//				if (check.equalsIgnoreCase("/")) {
//					path = servletContext.getRealPath("").replace("build/", "");
//					System.out.println(path);
//					System.out.println("111:" + servletContext.getRealPath(""));
//					path += "/"; // Adding trailing slash for Mac systems.
//				}
//				directory = new File(path + "\\" + general.getFilename());
//				boolean temp = directory.exists();
//				if (!temp) {
//					temp = directory.mkdir();
//				}
//				if (temp) {
//					// We need to transfer to a file
//					CommonsMultipartFile photoInMemory = general.getImg();
//					System.out.println("LLLLLLLLLLLLLLyLLLLLLL"+general.getImg().getOriginalFilename());
//
//					String fileName = photoInMemory.getOriginalFilename();	//get the file's name
//					// could generate file names as well
//
//					File localFile = new File(directory.getPath(), fileName);
//
//					// move the file from memory to the file
//
//					photoInMemory.transferTo(localFile);
//					general.setFilename(localFile.getPath());
//					System.out.println("File is stored at" + localFile.getPath());
//					System.out.print("registerNewUser");
//					System.out.println("LLLLLLLLLLLLLLyLLLLLLL"+general.getName());
//					generalDao.addGeneral(general);
//				} else {
//					System.out.println("Failed to create directory!");
//				}
//			}
//
//		
//		return new ModelAndView("admin-addsuccess");
//	}
//}
