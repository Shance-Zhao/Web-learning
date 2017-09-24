package com.shopping.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="order_table")
public class Order {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderNumber", unique = true, nullable = false)
	private String orderNumber;
	
	@Column(name = "orderDate")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@Column(name = "count")
	private static int count=0;
	
//	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH} , optional = true)
//	@JoinColumn(name="cart_id")
//	private Cart cart;
//	
//	public Cart getCart(){
//		return cart;
//	}
//	
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}
//	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private Set<Book> orderBooks = new HashSet<Book>();
//	
//	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//	private Set<Food> orderFoods = new HashSet<Food>();
//	
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private Set<Product> products = new HashSet<Product>();
	
	public Order()
	{
		count++;
		orderNumber="abc"+count;
	}
	
	public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
//    public Set<Book> getOrderBooks() {
//        return orderBooks;
//    }
//
//    public void setOrderBooks(Set<Book> orderBooks) {
//        this.orderBooks = orderBooks;
//    }
//
//    public Set<Food> getOrderFoods() {
//        return orderFoods;
//    }
//
//    public void setOrderFoods(Set<Food> orderFoods) {
//        this.orderFoods = orderFoods;
//    }

    public Set<Product> getOrderGenerals() {
        return products;
    }

    public void setOrderGenerals(Set<Product> products) {
        this.products = products;
    }
}
