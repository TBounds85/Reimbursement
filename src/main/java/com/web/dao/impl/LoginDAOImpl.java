package com.web.dao.impl;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

		try {

			s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			// storing result <~~ of the query
			Login result = s.createQuery("FROM com.web.model.Login L WHERE L.username = :username", Login.class)
					.setParameter("username", username).getSingleResult();

			tx.commit(); // commits query (Data Available for parsing)

			// checks password are exact match
			boolean checker = password.equals(result.getPassword());
			if (checker == true) {
				user.setEmployeeId(result.getEmployeeId());
			}
			s.close();
			return user.getEmployeeId();

		} catch (HibernateException e) {
			e.printStackTrace(); 

		} catch (NoResultException e) {
			return -1; // send to redirect to invalid.html (invalid Username)
		}
		
		return user.getEmployeeId();
	}

}
