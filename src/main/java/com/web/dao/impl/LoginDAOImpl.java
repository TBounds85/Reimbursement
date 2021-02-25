package com.web.dao.impl;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.web.dao.LoginDAO;
import com.web.model.Login;
import com.web.util.HibernateSessionFactory;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public int validate(String username, String password) {

		Session s;
		Login user = new Login();
		user.setUsername(username.toLowerCase());
		user.setPassword(password);
		user.setEmployeeId(-1); //set to -1 to cause redirect of invalid credentials
		user.setManager(false);
		try {

			s = HibernateSessionFactory.getSession();
			EntityTransaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			// storing result <~~ of the query
//			Login result = s.createQuery("FROM com.web.model.Login L WHERE L.username = :username", Login.class)
//					.setParameter("username", username).getSingleResult();
			Login result = s.load(Login.class, username);
			
//			tx.commit(); // commits query (Data Now Available for parsing)

			// checks password are exact match
			boolean checker = password.equals(result.getPassword());
			
			if (checker == true) {
				user.setEmployeeId(result.getEmployeeId());
				user.setManager(result.isManager());
				s.save(user);
			}
			
			return user.getEmployeeId();

		} catch (HibernateException e) {
			e.printStackTrace(); //something is wrong with Query

		} catch (NoResultException e) {
			return -1; // send to redirect to invalid.html (invalid Username)
		}
		return user.getEmployeeId();
	}

	@Override
	public boolean checkIfManager() {
		// TODO Auto-generated method stub
		return false;
	}

}
