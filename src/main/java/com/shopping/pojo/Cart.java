//package com.shopping.pojo;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="cart_table")
//public class Cart {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "CartID", unique = true, nullable = false)
//	private long CartID;
//	
//	public Cart(){}
////	@Column(name = "itemName")
////    private String itemName;
//	
////	@Column(name = "itemPrice")
////    private float itemPrice;
//	
//	@Column(name = "itemQuantity")	//Book's quantity
//    private int itemQuantity;
//	
////	@Column(name = "itemFQuantity")	//Food's quantity
////    private int itemFQuantity;
////	
////	@Column(name = "itemGQuantity")	//General item's quantity
////    private int itemGQuantity;
//	
//	@Column(name = "itemAllSale")	//All price
//    private float itemAllSale;
//	
//	
//	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//	//One-way relationship，don't need "mappedBy"  		New					Refresh
//    private Set<Product> cartProducts = new HashSet<Product>();
//	
////	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
////	private Set<Food> cartFoods = new HashSet<Food>();
////	
////	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
////	private Set<General> cartGenerals = new HashSet<General>();
////	
//	
////	@OneToMany(mappedBy="cart",cascade =CascadeType.ALL)
////	private Set<Order> orders = new HashSet<Order>();
//	
//	@OneToOne(optional = false, cascade = CascadeType.REFRESH)//, mappedBy = "cart"
//	@JoinColumn(name="personID",referencedColumnName="personID",unique=true)
//	// option=true means it can be null
//	private Customer customer;
//
//    
//    
//    public long getCartID() {
//		return CartID;
//	}
//
//	public int getItemQuantity()
//    {
//    	return itemQuantity;
//    }
//    
////    public void setItemBQuantity(int itemBQuantity)
////    {
////    	this.itemBQuantity = itemBQuantity;
////    }
////    
////    public int getItemFQuantity() {
////        return itemFQuantity;
////    }
////
////    public void setItemFQuantity(int itemFQuantity) {
////        this.itemFQuantity = itemFQuantity;
////    }
////
////    public int getItemGQuantity() {
////        return itemGQuantity;
////    }
////
////    public void setItemGQuantity(int itemGQuantity) {
////        this.itemGQuantity = itemGQuantity;
////    }
//    
//    public float getitemAllSale()
//    {
//    	return itemAllSale;
//    }
//    
//    public void setItemName(float itemAllSale){
//    	this.itemAllSale=itemAllSale;
//    }
//     
////    public Set<Book> getCartBooks() {
////        return cartBooks;
////    }
////
////    public void setCartBooks(Set<Book> cartBooks) {
////        this.cartBooks = cartBooks;
////    }
////
////    public Set<Food> getCartFoods() {
////        return cartFoods;
////    }
////
////    public void setCartFoods(Set<Food> cartFoods) {
////        this.cartFoods = cartFoods;
////    }
////
////    public Set<General> getCartGenerals() {
////        return cartGenerals;
////    }
////
////    public void setCartGenerals(Set<General> cartGenerals) {
////        this.cartGenerals = cartGenerals;
////    }
//    
//    public Customer getCustomer()
//    {
//    	return customer;
//    }
//    
//    public void setCustomer(Customer customer){
//    	this.customer=customer;
//    }
//
//	public Set<Product> getCartProducts() {
//		return cartProducts;
//	}
//
//	public void setCartProducts(Set<Product> cartProducts) {
//		this.cartProducts = cartProducts;
//	}
//    
//    
//    
//    
//  //Add books
////    public void addBooks(Book book){
////        if(!this.cartBooks.contains(book)){
////            this.cartBooks.add(book);
////            this.itemBQuantity++;
////            //book.itemQuantity--;??
////        }
////    }
////    
//    //Delete books
//    
//}
