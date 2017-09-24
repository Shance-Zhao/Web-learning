package com.shopping.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.shopping.pojo.Order;

public class OrderDAO extends DAO {

	public Order addOrder(Order order) throws Exception {
		try {
			begin();
			getSession().save(order);
			commit();
			return order;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating orders" + e.getMessage());
		}
	}
	
	public void delete(Order order)
	{
		begin();
		getSession().delete(order);
		commit();
	}
	
	public List<Order> show(){
		begin();
		Query q = getSession().createQuery("from Order");
		List<Order> orders = q.list();
		commit();
		return orders;
	}
}
