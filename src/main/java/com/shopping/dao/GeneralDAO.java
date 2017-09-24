package com.shopping.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.shopping.pojo.General;

public class GeneralDAO extends DAO{
	//Admin
			public General addGeneral(General general) throws Exception {
				try {
					begin();
				//	general.setTtype(TTyp.);
					getSession().save(general);
					commit();
					return general;
				} catch (HibernateException e) {
					rollback();
					throw new Exception("Exception while creating GeneralItems" + e.getMessage());
				}
			}
			public General[] addGeneral(General[] generals) throws Exception {
				try {
					begin();
					Session session=getSession();
//					int size=generals.length;
//					for(int i=0;i<size;i++){
//							session.save(generals[i]);
//					}
					for(General gen:generals){
						session.save(gen);
					}
					commit();
					return generals;
				} catch (HibernateException e) {
					rollback();
					throw new Exception("Exception while creating GeneralItems" + e.getMessage());
				}
			}
			
			//Show all the general items
			public List<General> showAll(){
				begin();
				Query q = getSession().createQuery("from General");
				List<General> general = q.list();
				commit();
				return general;
			}
			

			public List<General> getByName(String name){
				begin();
				Criteria crit = getSession().createCriteria(General.class);
				crit.add(Restrictions.like("name", "%"+name+"%"));
				List<General> results = crit.list();
				return results;
//				Query q = getSession().createQuery("from General where name = :name");
//				q.setString("name", name);
//				Set<General> generals = (Set<General>) q.list();
//				if(generals!=null){
//					commit();
//					return generals;
//				}else{
//					return null;
//				}
				
			}
			
}
