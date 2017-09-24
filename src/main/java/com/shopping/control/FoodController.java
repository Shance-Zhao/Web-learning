package com.shopping.control;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.dao.FoodDAO;
import com.shopping.dao.ProductDAO;
import com.shopping.pojo.Food;
import com.shopping.pojo.Product;
import com.shopping.pojo.Product.Type;
import com.shopping.validator.FoodValidator;

@Controller
@RequestMapping("/food/*")
public class FoodController {
	private String[] name;
//	private String[] img;
	private String[] quantity;
	private String[] price;
	private String[] productionDate;
	private String[] saleprice;
	private String[] description;
	private String[] shelflife;
	private String[] filename;

	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@Autowired
	@Qualifier("foodDao")
	FoodDAO foodDao;

	@Autowired
	@Qualifier("foodValidator")
	FoodValidator foodValidator;

	@Autowired
	ServletContext servletContext;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(foodValidator);
	}

	// go to addTable
	@RequestMapping(value = "/addFood", method = RequestMethod.GET)
	protected ModelAndView addGeneralTable() throws Exception {

		return new ModelAndView("admin-addFood");
	//	return new ModelAndView("addTable");
	}


	// go to success page
	@RequestMapping(value = "/addsuccess", method = RequestMethod.POST)
	protected ModelAndView addSuccess(HttpServletRequest request,@RequestParam("img") List<CommonsMultipartFile> img) throws Exception {
		SimpleDateFormat shelDate = new SimpleDateFormat( "MM/dd/yyyy");
		int num = Integer.parseInt(request.getParameter("rows"));
		java.util.Date startDate = null;
		java.util.Date endDate = null;
		//String endDate="";
		
		Food[] foods = new Food[num];
		// foodDao.addGeneral(foods);
		System.out.println("length.." + foods.length);
		for (int i = 0; i < num; i++) 
		{
			foods[i] = new Food();
			name = request.getParameterValues("name");
			quantity = request.getParameterValues("quantity");
			saleprice = request.getParameterValues("saleprice");
			description = request.getParameterValues("description");
			price = request.getParameterValues("price");
			shelflife = request.getParameterValues("selflifeDate");
			productionDate = request.getParameterValues("productionDate");
			
			foods[i].setName(name[i]);
			foods[i].setImg(img.get(i));
			foods[i].setQuantity(Integer.parseInt(quantity[i]));
			foods[i].setPrice(Float.valueOf(price[i]));
			foods[i].setSaleprice(Float.valueOf(saleprice[i]));
			foods[i].setDescription(description[i]);
			foods[i].setType(Type.Food);
			
			System.out.println(shelflife[i]);
			endDate=shelDate.parse(shelflife[i]);
			java.sql.Date end = new java.sql.Date(endDate.getTime());
			System.out.println("end   : "+end);
			foods[i].setShelflife(end);
	//		foods[i].setShelflife(shelflife[i]);
			
			startDate=shelDate.parse(productionDate[i]);
			java.sql.Date start = new java.sql.Date(startDate.getTime());
			foods[i].setProductionDate(start);
			File directory;
			
			String check = File.separator; // Checking if system is linux"/"
											// based or windows"\" based by
											// checking seprator used.
			String path = null;
			if (check.equalsIgnoreCase("\\")) {
				path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
			//	path="C:/Users/zhaos/Documents/STS/Final_Shopping/src/main/resources/Picture";
				// it's calculate start with where current servlet in tomcat
				// // so we need to replace build in the path
				System.out.println(path);
			}

			if (check.equalsIgnoreCase("/")) {
				path = servletContext.getRealPath("").replace("build/", "");
				System.out.println("100000000011"+path);
				System.out.println("111:" + servletContext.getRealPath(""));
				path += "/"; // Adding trailing slash for Mac systems.
			}
			directory = new File(path + "\\Food");
			System.out.println("Food's getFilename "+directory);
			boolean temp = directory.exists();
			if (!temp) {
				temp = directory.mkdir();
			}
			if (temp) {
				// We need to transfer to a file
				CommonsMultipartFile photoInMemory = foods[i].getImg();

				String fileName = photoInMemory.getOriginalFilename();	//get the file's name
				// could generate file names as well

				File localFile = new File(directory.getPath(), fileName);

				// move the file from memory to the file

				photoInMemory.transferTo(localFile);
				System.out.println("File is stored at" + localFile.getPath());
				foods[i].setFilename(localFile.getPath());
				foodDao.addFood(foods[i]);
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		List<Product>products = productDao.showAll();
		request.getSession().setAttribute("products",products);
		return new ModelAndView("admin-addsuccess");
	}
}
