package com.web.dao.impl;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.web.dao.LoginDAO;
import com.web.model.Employee;
import com.web.model.Login;
import com.web.util.HibernateSessionFactory;

public class LoginDAOImpl implements LoginDAO {
	
	Logger log = Logger.getLogger(LoginDAOImpl.class);
	
	@Override
	public int validate(String username, String password) {

		Session s = null;
		Login user = new Login();
		user.setUsername(username.toLowerCase());
		user.setPassword(password);
		user.setEmployeeId(-1); // set to -1 to cause redirect of invalid credentials

		try {

			s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			// storing result <~~ of the query
//			Login result = s.createQuery("FROM com.web.model.Login L WHERE L.username = :username", Login.class)
//					.setParameter("username", username).getSingleResult();
			Login result = s.load(Login.class, username);

			

			// checks password are exact match
			boolean checker = password.equals(result.getPassword());

			if (checker == true) {
				user.setEmployeeId(result.getEmployeeId());
				s.save(user);
			}

			return user.getEmployeeId();

		} catch (HibernateException e) {
			log.error("Something is wrong with the Query Login DAO Impl"); // something is wrong with Query
			e.printStackTrace();
			s.clear();
			return -1;

		} catch (NoResultException e) {
			log.info("Returned no results (invalid Username/Password)");
			s.clear();
			return -1; // send to redirect to invalid.html (invalid Username)
		}
	}

	@SuppressWarnings("finally")
	public Employee setupEmployee(int employeeId) {
		
		Employee e =new Employee();
		
		
		try{
			Session s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

//			 storing result <~~ of the query
			e = s.createQuery("FROM com.web.model.Employee L WHERE L.employeeId = :employeeId", Employee.class)
					.setParameter("employeeId", employeeId).getSingleResult();
			tx.commit(); // commits query (Data Now Available for parsing)
//			e = s.load(Employee.class, employeeId);
			s.save(e);
			}finally {
				return e;
			}
		
		}

	@Override
	public boolean checkIfManager() {
		// TODO Auto-generated method stub
		return false;
	}

}
