package com.web.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.web.dao.LoginVerificationDAO;
import com.web.model.LoginVerification;
import com.web.util.HibernateSessionFactory;

public class LoginVerificationDAOImpl implements LoginVerificationDAO{

	@Override
	public boolean validate(String username, String password) {
			boolean checker = false;
			Session s;
			Transaction tx = null;
			
			try {
				s = HibernateSessionFactory.getSession();
				tx = s.beginTransaction();
				String hql = "SELECT password FROM loginverification WHERE username= :username";
				 Query<?> query = s.createQuery(hql);
				query.setParameter("username", username);
				
				
//				tx.commit(); //commits query
				LoginVerification results = (LoginVerification) query.getSingleResult();
				
				
//				LoginVerification user = new LoginVerification();
//				user.setUsername(username);
//				user.setPassword(password);
				
//				user.getPassword().equals(anObject)
				
				//before
				System.out.println(results+" <~~ this is results straight from DB with HQL");
				//after
				String Pass=results.toString();
				System.out.println(Pass+" <~~this is results.toString()");
				
				
				
				checker=password.equals(Pass);
				
				return checker;

			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();

			}
			return checker;
		}
	
}
