package com.shopping.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.shopping.pojo.Product;

public class ProductDAO extends DAO{

	//Admin
		public Product addProduct(Product product) throws Exception {
			try {
				begin();
				getSession().save(product);
				commit();
				return product;
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Exception while creating Product" + e.getMessage());
			}
		}
		public Product[] addProduct(Product[] products) throws Exception {
			try {
				begin();
				Session session=getSession();
//				int size=generals.length;
//				for(int i=0;i<size;i++){
//						session.save(generals[i]);
//				}
				for(Product p:products){
					session.save(p);
				}
				commit();
				return products;
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Exception while creating Products" + e.getMessage());
			}
		}

		public void delete(int productID)
		{
			begin();
			Query q = getSession().createQuery("from Product where productID = :productID");
			q.setInteger("productID", productID);
			Product product = (Product) q.uniqueResult();
			getSession().delete(product);
			commit();
		}
		
		public List<Product> showAll(){
			begin();
			Query q = getSession().createQuery("from Product");
			List<Product> products = q.list();
			commit();
			return products;
		}
		
		public List<Product> searchPro(String name){
			begin();
			Query q = getSession().createQuery("from Product where name like :name");
			q.setString("name", "%"+name+"%");
			List<Product> products = q.list();
			commit();
			return products;
		}
		
		public Product get(String name)
		{
			begin();
			Query q = getSession().createQuery("from Product where name = :name");
			q.setString("name", name);
			Product product = (Product) q.uniqueResult();
			if(product!=null){
				commit();
				return product;
			}else{
				return null;
			}
		}
		
		public Product getById(long productID){
			begin();
			Query q = getSession().createQuery("from Product where productID = :productID");
			q.setLong("productID", productID);
			Product product = (Product) q.uniqueResult();
			if(product!=null){
				commit();
				return product;
			}else{
				return null;
			}
		}
		//Customer
		
}
