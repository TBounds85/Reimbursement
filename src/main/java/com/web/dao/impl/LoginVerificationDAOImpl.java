package com.web.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.web.dao.LoginVerificationDAO;
import com.web.model.loginverification;
import com.web.util.HibernateSessionFactory;

public class LoginVerificationDAOImpl implements LoginVerificationDAO{

	@Override
	public boolean validate(String username, String password) {
		
			Session s = null;
			Transaction tx = null;

			try {
				s = HibernateSessionFactory.getSession();
				tx = s.beginTransaction();

				Object pulledPassword = s.createQuery("SELECT password FROM loginverification WHERE username= :username", loginverification.class)
				.setParameter("username", username);
				tx.commit();
				
				String Pass=pulledPassword.toString();
				
				
				if (password.equals(Pass)) {
					return true;
				} else
					return false;

			} catch (HibernateException e) {
				e.printStackTrace();
				tx.rollback();

			}
			return false;
		}
	
}
