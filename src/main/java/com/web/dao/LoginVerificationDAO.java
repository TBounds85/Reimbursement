package com.web.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.web.model.loginverification;
import com.web.util.HibernateSessionFactory;

public interface LoginVerificationDAO {

	static boolean validate(String username, String password) {
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
						
			Object pulledPassword = s.createQuery("FROM loginverification where username="+username, loginverification.class).getResultList();
			tx.commit();
			
			if(password == pulledPassword) {
				return true;
			}else 
				return false;
			
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		return false;
		
		
	}
	
}
