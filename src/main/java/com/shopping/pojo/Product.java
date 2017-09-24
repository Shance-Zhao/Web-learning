package com.shopping.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "product_table")
@Inheritance(strategy = InheritanceType.JOINED) // table per subclass
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productID", unique = true, nullable = false)
	private long productID;

	@Column(name = "name")
	private String name;

	@Transient
	private CommonsMultipartFile img; // for DataBinder to bind <input
										// type="file".../>
										// will not be mapped for Hibernate as
										// we store the file in the FileSystem
										// file will be placed into this field
										// by DataBinder
										// file is in the memory. needs to be
										// transferred to the FileSystem using
										// java.io.file

	// @Transient
	// private List<CommonsMultipartFile> photos;
	@Column(name= "filename")
	private String filename;
	
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private float price; // real price

	@Column(name = "saleprice")
	private float saleprice;

	@Column(name = "description")
	private String description;

	@Column(name = "clickNum")
	protected int clickNum;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;

	public Product() {

	}

	public Product(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// for Hibernate

	public void setImg(CommonsMultipartFile img) {
		this.img = img;
	}

	// public void setPhotos(List<CommonsMultipartFile> photos) {
	// this.photos = photos;
	// }
	//
	// public List<CommonsMultipartFile> getPhotos() {
	// return photos;
	// }
	
	public CommonsMultipartFile getImg() {
		return img;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(float saleprice) {
		this.saleprice = saleprice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public long getProductID() {
		return productID;
	}

	public static enum Type {
		Book("Book"), Food("Food"), General("General");
		private String name;

		private Type(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

}
