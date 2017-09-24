package com.shopping.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.shopping.pojo.Admin;

public class AdminDAO extends DAO
{
	//Get admin's name and password
	public Admin getAdNandP(String adminName,String adminPassword)
	{
		begin();
		Query q = getSession().createQuery("from Admin where adminName = :adminName and adminPassword = :adminPassword");
		q.setString("adminName", adminName);
		q.setString("adminPassword", adminPassword);
		Admin admin = (Admin) q.uniqueResult();
		if(admin!=null){
			commit();
			return admin;
		}
		else{
			return null;
		}
	}
	//Add admin
	public Admin addAdmin(Admin admin) throws Exception {
		try {
			begin();
			getSession().save(admin);
			commit();
			return admin;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating admin" + e.getMessage());
		}
	}
	public void deleteAdmin(Admin admin)
	{
		begin();
		getSession().delete(admin);
		commit();
	}
	public List<Admin> show()
	{
		begin();
		Query q = getSession().createQuery("from Admin");
		List<Admin> admins = q.list();
		commit();
		return admins;
	}
}
