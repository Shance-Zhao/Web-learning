package com.shopping.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.shopping.pojo.Book;

public class BookDAO extends DAO {

	//Admin
	public Book addBook(Book book) throws Exception {
		try {
			begin();
			getSession().save(book);
			commit();
			return book;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating book" + e.getMessage());
		}
	}
	public Book[] addBook(Book[] books) throws Exception {
		try {
			begin();
			Session session=getSession();
//			int size=generals.length;
//			for(int i=0;i<size;i++){
//					session.save(generals[i]);
//			}
			for(Book b:books){
				session.save(b);
			}
			commit();
			return books;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating Books" + e.getMessage());
		}
	}
	
	public void delete(Book book)
	{
		begin();
		getSession().delete(book);
		commit();
	}
	
	public List<Book> showAll(){
		begin();
		Query q = getSession().createQuery("from Book");
		List<Book> books = q.list();
		commit();
		return books;
	}
	
	public Book getById(String name){
		begin();
		Query q = getSession().createQuery("from Book where name = :name");
		q.setString("name", name);
		Book book = (Book) q.uniqueResult();
		if(book!=null){
			commit();
			return book;
		}else{
			return null;
		}
	}
	
	//Customer
	
}
