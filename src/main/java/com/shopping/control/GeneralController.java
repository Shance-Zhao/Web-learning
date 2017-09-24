package com.shopping.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.dao.GeneralDAO;
import com.shopping.dao.ProductDAO;
import com.shopping.pojo.General;
import com.shopping.pojo.Product;
import com.shopping.pojo.Product.Type;
import com.shopping.validator.GeneralValidator;

@Controller
@RequestMapping("/general/*")
public class GeneralController {
	private String[] name;
	private String[] quantity;
	private String[] price;
	private String[] saleprice;
	private String[] description;
	private String[] type;
	private String[] filename;
	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@Autowired
	@Qualifier("generalDao")
	GeneralDAO generalDao;

	@Autowired
	@Qualifier("generalValidator")
	GeneralValidator generalValidator;

	@Autowired
	ServletContext servletContext;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(generalValidator);
	}


	// go to addTable
	@RequestMapping(value = "/addGeneral", method = RequestMethod.GET)
	protected ModelAndView addGeneralTable() throws Exception {

		return new ModelAndView("admin-addGeneral");
	}


	// go to success page
	@RequestMapping(value = "/addsuccess", method = RequestMethod.POST)
	protected ModelAndView addSuccess(HttpServletRequest request,@RequestParam("img") List<CommonsMultipartFile> img ) throws Exception {
	//	CommonsMultipartFile[] img;
		int num = Integer.parseInt(request.getParameter("rows"));
		General[] generals = new General[num];
		// generalDao.addGeneral(generals);
		System.out.println("length.." + generals.length);
		for (int i = 0; i < num; i++) 
		{
			generals[i] = new General();
			name = request.getParameterValues("name");
			quantity = request.getParameterValues("quantity");
			saleprice = request.getParameterValues("saleprice");
			description = request.getParameterValues("description");
			price = request.getParameterValues("price");
		//	type
			
			generals[i].setName(name[i]);
			generals[i].setImg(img.get(i));
			generals[i].setQuantity(Integer.parseInt(quantity[i]));
			generals[i].setPrice(Float.valueOf(price[i]));
			generals[i].setSaleprice(Float.valueOf(saleprice[i]));
			generals[i].setDescription(description[i]);
			generals[i].setType(Type.General);
			
			File directory;
			String check = File.separator; // Checking if system is linux"/"
												// based or windows"\" based by
												// checking seprator used.
			String path = null;
			if (check.equalsIgnoreCase("\\")) {
				path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
				// it's calculate start with where current servlet in tomcat
				// // so we need to replace build in the path
				System.out.println(path);
				System.out.println("111:" + servletContext.getRealPath(""));

			}

			if (check.equalsIgnoreCase("/")) {
				path = servletContext.getRealPath("").replace("build/", "");
				System.out.println(path);
				System.out.println("111:" + servletContext.getRealPath(""));
				path += "/"; // Adding trailing slash for Mac systems.
			}
				directory = new File(path + "\\General");
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = generals[i].getImg();

					String fileName = photoInMemory.getOriginalFilename();	//get the file's name
					// could generate file names as well

					File localFile = new File(directory.getPath(), fileName);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					System.out.println("File is stored at " + localFile.getPath());
					generals[i].setFilename(localFile.getPath());
					System.out.print("registerNewUser");
					generalDao.addGeneral(generals[i]);
				} else {
					System.out.println("Failed to create directory!");
				}
			}

		List<Product>products = productDao.showAll();
		request.getSession().setAttribute("products",products);
		return new ModelAndView("admin-addsuccess");
	}
	
	//Search
			@RequestMapping(value="/search", method=RequestMethod.POST)
			protected ModelAndView Search(HttpServletRequest request) throws Exception {

				// session.removeAttribute("admin");
				//generalDao.getByName(request.getParameter("gname"));
				List<General> searchgeneral = generalDao.getByName(request.getParameter("gname"));
				return new ModelAndView("general-search","searchgeneral",searchgeneral);

			}
		
}
