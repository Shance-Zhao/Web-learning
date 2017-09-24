//package com.shopping.dao;
//
//import java.util.List;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//
//import com.shopping.exception.CartException;
//import com.shopping.exception.ProductException;
//import com.shopping.pojo.Cart;
//import com.shopping.pojo.Product;
//
//
//public class CartDAO extends DAO {
//
//	
//	public Cart addCart(Cart cart) throws Exception {
//		try {
//			begin();
//			getSession().save(cart);
//			commit();
//			return cart;
//		} catch (HibernateException e) {
//			rollback();
//			throw new Exception("Exception while creating carts" + e.getMessage());
//		}
//	}
//	
//	public Cart getThis(String CartID) throws Exception{
//		begin();
//		Query q = getSession().createQuery("from Cart where CartID = :CartID");
//		Cart cart = (Cart) q.uniqueResult();
//		commit();
//		return cart;
//	}
//	
//	public void delete(Cart cart)
//	{
//		begin();
//		getSession().delete(cart);
//		commit();
//	}
//	
//	public List<Cart> show(){
//		begin();
//		Query q = getSession().createQuery("from Cart");
//		List<Cart> carts = q.list();
//		commit();
//		return carts;
//	}
//	public Product addProduct(Product product) throws Exception{
//		try {
//			begin();
//			//Query q = getSession().createQuery("")
//		//	getSession().save(product);
//			Cart cart = new Cart();
//			cart.getCartProducts().add(product);
//			commit();
//			return product;
//		} catch (HibernateException e) {
//			rollback();
//			throw new Exception("Exception while adding product" + e.getMessage());
//		}
//	}
//}
