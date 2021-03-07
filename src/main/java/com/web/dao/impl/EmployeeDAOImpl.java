package com.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.web.dao.EmployeeDAO;
import com.web.model.EmployeeDetails;
import com.web.model.Request;
import com.web.util.HibernateSessionFactory;

public class EmployeeDAOImpl implements EmployeeDAO {

	Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	@Override
	public boolean submitRequest(int employeeId, double requestedAmount, String reason, int managerId) {
		try {

			Session s = HibernateSessionFactory.getSession();
			s.beginTransaction();

			Request request = new Request();
			request.setRequestId(0);
			request.setEmployeeid(employeeId);
			request.setRequestedAmount(requestedAmount);
			request.setReason(reason);
			request.setStatus("Pending");
			request.setManagerId(managerId);

			s.saveOrUpdate(request);
			s.getTransaction().commit();
			log.info("Request submitted successfully");
			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Request> pendingRequests(int employeeId) {

		List<Request> list = new ArrayList<>();
		try {
			Session s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			Query<Request> query = s
					.createQuery("FROM com.web.model.Request R WHERE R.employeeid = :employeeid and R.status = :status", Request.class)
					.setParameter("employeeid", employeeId).setParameter("status", "Pending");
			list = query.list();

			tx.commit();
			return list;

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Request> resolvedRequests(int employeeId) {
		List<Request> list = new ArrayList<>();
		try {
			Session s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			Query<Request> query = s.createQuery(
					"FROM com.web.model.Request R WHERE R.employeeid = :employeeid and R.status != :status",
					Request.class).setParameter("employeeid", employeeId).setParameter("status", "Pending");
			list = query.list();

			tx.commit();
			return list;

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void updateInformation(int employeeId, double contact, String address, String city, String state,
			int zipcode) {
		try {

			Session s = HibernateSessionFactory.getSession();
			s.beginTransaction();

			EmployeeDetails ED = new EmployeeDetails();
			ED.setContact(contact);
			ED.setAddress(address);
			ED.setCity(city);
			ED.setState(state);
			ED.setZipcode(zipcode);

			s.saveOrUpdate(ED);
			s.getTransaction().commit();
			return;

		} catch (HibernateException e) {
			e.printStackTrace();
			return;
		}

	}

}
