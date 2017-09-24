package com.shopping.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.shopping.pojo.Food;

public class FoodDAO  extends DAO {
	
	
	//Admin
		public Food addFood(Food food) throws Exception {
			try {
				begin();
				getSession().save(food);
				commit();
				return food;
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Exception while creating foods" + e.getMessage());
			}
		}
		
		public void delete(Food food)
		{
			begin();
			getSession().delete(food);
			commit();
		}
		
		public List<Food> showAll(){
			begin();
			Query q = getSession().createQuery("from Food");
			List<Food> foods = q.list();
			commit();
			return foods;
		}
		
		
		public Set<Food> getById(String name){
			begin();
			Query q = getSession().createQuery("from Food where name = :name");
			q.setString("name", name);
			Set<Food> foods = (Set<Food>) q.list();
			if(foods!=null){
				commit();
				return foods;
			}else{
				return null;
			}
		}
		//Customer
		
}
