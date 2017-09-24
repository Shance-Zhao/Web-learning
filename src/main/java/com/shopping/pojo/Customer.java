package com.shopping.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer_table")
@PrimaryKeyJoinColumn(name = "personID")
public class Customer extends Person{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "CustomerID", unique = true, nullable = false)
//	private int CustomerID;
//

	@Column(name = "userName")
	private String userName;

	@Column(name = "customerPassword")
	private String customerPassword;

	@Column(name = "customerEmail")
	private String customerEmail;
	
	@Column( name= "isStudent")
	private boolean isStudent;

//	@Column(name = "customerGender")
//	private String customerGender;

	@Column(name = "customerAddress1")
	private String customerAddress1;

	@Column(name = "customerAddress2")
	private String customerAddress2;

	// @Column(name="customerPaymentType")
	// private String customerPaymentType;

	@Column(name = "customerCardNum")
	private long customerCardNum;

	@Column(name = "customerPayExpiration")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date customerPayExpiration;

	// one customer one cart
//	@OneToOne(optional = true, cascade = CascadeType.ALL) 
//	// option=true means it can be null
//	@JoinColumn(name="Cart_ID",referencedColumnName="CartID",unique=true)
//	private Cart cart;

	@OneToMany(cascade = { CascadeType.ALL })
	private Set<Order> orders = new HashSet<Order>();

	public Customer() {

	}

	public Customer(String userName, String customerPassword, String customerEmail,boolean isStudent) {
		this.userName = userName;
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
		this.isStudent=isStudent;
	//	this.cart=new Cart();
	}

//	public Customer(String customerName, String customerPassword, String customerEmail, String customerGender) {
//		this.customerName = customerName;
//		this.customerPassword = customerPassword;
//		this.customerEmail = customerEmail;
//		this.customerGender = customerGender;
//	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public boolean getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

//	public String getCustomerGender() {
//		return customerGender;
//	}
//
//	public void setCustomerGender(String customerGender) {
//		this.customerGender = customerGender;
//	}

	public String getCustomerAddress1() {
		return customerAddress1;
	}

	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}

	public String getCustomerAddress2() {
		return customerAddress2;
	}

	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}

	public long getCustomerCardNum() {
		return customerCardNum;
	}

	public void setCustomerCardNum(long customerCardNum) {
		this.customerCardNum = customerCardNum;
	}

	public Date getCustomerPayExpiration() {
		return customerPayExpiration;
	}

	public void setCustomerPayExpiration(Date customerPayExpiration) {
		this.customerPayExpiration = customerPayExpiration;
	}

//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
