package com.web.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.web.dao.LoginVerificationDAO;
import com.web.model.Login;
import com.web.util.HibernateSessionFactory;

public class LoginVerificationDAOImpl implements LoginVerificationDAO{

	@Override
	public boolean validate(String username, String password) {
			boolean checker = false;
			Session s;
			
			
			try {
				
				s = HibernateSessionFactory.getSession();
				Transaction tx = s.beginTransaction();
				
				Login results =  s.createQuery("FROM loginverification L WHERE L.username = :username", Login.class)
						.setParameter("username", username).getSingleResult();
				
				
				//same as above
//				String hql = "SELECT lv.password FROM loginverification lv WHERE lv.username= :username";
//				 Query<?> query = s.createQuery(hql);
//				query.setParameter("username", username);
//				
//				
//				
//				Login results = (Login) query.getSingleResult();
				
				
//				LoginVerification user = new LoginVerification();
//				user.setUsername(username);
//				user.setPassword(password);
				
//				resu
				tx.commit(); //commits query
				//before
				System.out.println(results+" <~~ this is results straight from DB with HQL");
				//after
				String Pass=results.toString();
				System.out.println(Pass+" <~~this is results.toString()");
				
				
				//checks password are exact match
				checker=password.equals(Pass);
				
				return checker;

			} catch (HibernateException e) {
				e.printStackTrace();
			

			}
			return checker;
		}
	
}
