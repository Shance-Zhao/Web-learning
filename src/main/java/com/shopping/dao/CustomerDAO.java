package com.shopping.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.shopping.exception.CustomerException;
//import com.shopping.pojo.Cart;
import com.shopping.pojo.Customer;

public class CustomerDAO extends DAO{

	public CustomerDAO(){}
	
	public Customer getNandP(String userName, String customerPassword)
	{
		begin();
		Query q = getSession().createQuery("from Customer where userName = :userName and customerPassword = :customerPassword");
		q.setString("userName", userName);
		q.setString("customerPassword", customerPassword);
		Customer customer = (Customer) q.uniqueResult();
		if(customer!=null){
			commit();
			return customer;
		}else{
			return null;
		}
	}
	public Customer getCustomerEmail(String customerEmail)
	{
		begin();
		Query q = getSession().createQuery("from Customer where customerEmail = :customerEmail");
		q.setString("customerEmail", customerEmail);
		Customer customer = (Customer) q.uniqueResult();
		commit();
		return customer;
	}
	public Customer addCustomer(Customer customer) throws Exception {
		try {
			begin();
			getSession().save(customer);
			commit();
			return customer;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating customers" + e.getMessage());
		}
	}
	
	public void delete(Customer customer)
	{
		begin();
		getSession().delete(customer);
		commit();
	}
	
	public List<Customer> show(){
		begin();
		Query q = getSession().createQuery("from customer_table");
		List<Customer> customers = q.list();
		commit();
		return customers;
	}
	
	public Customer register(Customer cus) throws CustomerException
	{
		try{
			begin();
			Customer customer = new Customer(cus.getUserName(),cus.getCustomerPassword(),cus.getCustomerEmail(),cus.getIsStudent());
			customer.setFirstName(cus.getFirstName());
			customer.setLastName(cus.getLastName());
			customer.setIsStudent(cus.getIsStudent());
//			Cart cart = new Cart();
//			cart.setCustomer(cus);
//			customer.setCart(cart);
			getSession().save(customer);
			
			commit();
			return customer;
		} catch (HibernateException e) {
			rollback();
			throw new CustomerException("Exception while creating user: " + e.getMessage());
		}
	}
}
