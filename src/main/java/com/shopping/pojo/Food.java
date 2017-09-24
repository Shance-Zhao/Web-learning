package com.shopping.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="food_table")
@PrimaryKeyJoinColumn(name = "productID")
public class Food extends Product{

	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "FoodID", unique = true, nullable = false)
//	private long FoodID;
	
//	@Column(name = "FoodName")
//    private String FoodName;
//	
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
	@Column(name = "productionDate")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date productionDate;
	
	@Column(name = "shelflife")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date shelflife;
	
	
	public Food(){}
	
//	public Food(String FoodName, String img, int quantity, float price, String description, Date shelflife)
//	{
//		this.FoodName=FoodName;
//		this.img = img;
//		this.quantity=quantity;
//		this.price=price;
//		this.description=description;
//		this.shelflife=shelflife;
//	}
	
//	 public String getFoodName() {
//	        return FoodName;
//	    }
//
//	    public void setFoodName(String FoodName) {
//	        this.FoodName = FoodName;
//	    }
//
//	    public String getImg() {
//	        return img;
//	    }
//
//	    public void setImg(String img) {
//	        this.img = img;
//	    }
//
//	    public int getQuantity() {
//	        return quantity;
//	    }
//
//	    public void setQuantity(int quantity) {
//	        this.quantity = quantity;
//	    }
//
//	    public float getPrice() {
//	        return price;
//	    }
//
//	    public void setPrice(float price) {
//	        this.price = price;
//	    }
//
//	    public float getSaleprice() {
//	        return saleprice;
//	    }
//
//	    public void setSaleprice(float saleprice) {
//	        this.saleprice = saleprice;
//	    }
//	    
	    public Date getShelflife() {
	        return shelflife;
	    }

	    public void setShelflife(Date shelflife) {
	        this.shelflife = shelflife;
	    }
		public Date getProductionDate() {
			return productionDate;
		}
		public void setProductionDate(Date productionDate) {
			this.productionDate = productionDate;
		}

//	    public String getDescription() {
//	        return description;
//	    }
//
//	    public void setDescription(String description) {
//	        this.description = description;
//	    }
//
//	    public int getClickNum() {
//	        return clickNum;
//	    }
//
//	    public void setClickNum(int clickNum) {
//	        this.clickNum = clickNum;
//	    }
}
