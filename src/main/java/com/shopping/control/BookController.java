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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.dao.BookDAO;
import com.shopping.dao.ProductDAO;
import com.shopping.dao.BookDAO;
import com.shopping.pojo.Book;
import com.shopping.pojo.Product;
import com.shopping.pojo.Product.Type;
import com.shopping.validator.BookValidator;
import com.shopping.validator.BookValidator;

@Controller
@RequestMapping("/book/*")
public class BookController {
	private String[] name;
	private String[] filename;
	private String[] quantity;
	private String[] price;
	private String[] saleprice;
	private String[] description;
	private String[] isbn;
	private String[] author;
	//private String[] type;

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@Autowired
	@Qualifier("bookDao")
	BookDAO bookDao;

	@Autowired
	@Qualifier("bookValidator")
	BookValidator bookValidator;

	@Autowired
	ServletContext servletContext;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(bookValidator);
	}

	private static List<Book> books = new ArrayList<Book>();

	// go to addTable
	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	protected ModelAndView addGeneralTable() throws Exception {

		return new ModelAndView("admin-addBook");
	}


	// go to success page
	@RequestMapping(value = "/addsuccess", method = RequestMethod.POST)
	protected ModelAndView addSuccess(HttpServletRequest request,@RequestParam("img") List<CommonsMultipartFile> img) throws Exception {
		System.out.println(request.getParameter("rows"));
		int num = Integer.parseInt(request.getParameter("rows"));
		Book[] books = new Book[num];
		// bookDao.addGeneral(books);
		System.out.println("length.." + books.length);
		for (int i = 0; i < num; i++) 
		{
			books[i] = new Book();
			name = request.getParameterValues("name");
			quantity = request.getParameterValues("quantity");
			saleprice = request.getParameterValues("saleprice");
			description = request.getParameterValues("description");
			price = request.getParameterValues("price");
			isbn = request.getParameterValues("isbn");
			author = request.getParameterValues("author");
		//	type
			
			books[i].setName(name[i]);
			books[i].setImg(img.get(i));
			books[i].setQuantity(Integer.parseInt(quantity[i]));
			books[i].setPrice(Float.valueOf(price[i]));
			books[i].setSaleprice(Float.valueOf(saleprice[i]));
			books[i].setDescription(description[i]);
			books[i].setIsbn(isbn[i]);
			books[i].setAuthor(author[i]);
			books[i].setType(Type.Book);
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
			}

			if (check.equalsIgnoreCase("/")) {
				path = servletContext.getRealPath("").replace("build/", "");
				System.out.println(path);
				System.out.println("111:" + servletContext.getRealPath(""));
				path += "/"; // Adding trailing slash for Mac systems.
			}
			directory = new File(path + "\\Book");
			boolean temp = directory.exists();
			if (!temp) {
				temp = directory.mkdir();
			}
			if (temp) {
				// We need to transfer to a file
				CommonsMultipartFile photoInMemory = books[i].getImg();

				String fileName = photoInMemory.getOriginalFilename();	//get the file's name
				// could generate file names as well

				File localFile = new File(directory.getPath(), fileName);

				// move the file from memory to the file

				photoInMemory.transferTo(localFile);
				System.out.println("File is stored at" + localFile.getPath());
				books[i].setFilename(localFile.getPath());
				bookDao.addBook(books[i]);
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		List<Product>products = productDao.showAll();
		request.getSession().setAttribute("products",products);
		return new ModelAndView("admin-addsuccess");
	}
}
