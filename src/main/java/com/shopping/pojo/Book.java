package com.shopping.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="book_table")
@PrimaryKeyJoinColumn(name = "productID")

public class Book extends Product{

	
	@Column(name = "isbn", unique = true, nullable = false)
	private String isbn;
	
//	@Column(name = "title")
//    private String title;
//	
	@Column(name = "author")
    private String author;
	
//	@Column(name = "img")
//	private String img;
//	
//	@Column(name = "quantity")
//	private int quantity;
//	
//	@Column(name = "price")
//    private float price;	//real price
//	
//	@Column(name = "saleprice")
//    private float saleprice;
//	
//	@Column(name = "description")
//	private String description;
//	
//	@Column(name = "clickNum")
//	private int clickNum;
//	
    public Book(){}
    
//    public Book(long isbn,String author)
//    {
//    	this.isbn=isbn;
//    	this.author=author;
//    }
//    public Book(long isbn,String title,String author, String img, float price, String description)
//    {
//    	super()
//    	this.isbn=isbn;
//    	this.title=title;
//    	this.author=author;
//    	this.img=img;
//    	this.price=price;
//    	this.description=description;
//    	
//    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

//    public float getPrice() {
//        return price;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }
//    
//    public float getSaleprice() {
//        return saleprice;
//    }
//
//    public void setSaleprice(float saleprice) {
//        this.saleprice = saleprice;
//    }
//    
//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//    
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getClickNum() {
//        return clickNum;
//    }
//
//    public void setClickNum(int clickNum) {
//        this.clickNum = clickNum;
//    }
}
