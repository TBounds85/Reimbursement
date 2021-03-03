package com.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.web.dao.ManagerDAO;
import com.web.model.Employee;
import com.web.model.Request;
import com.web.util.HibernateSessionFactory;

public class ManagerDAOImpl implements ManagerDAO {
	
	Logger log = Logger.getLogger(ManagerDAOImpl.class);
	
	@Override
	public List<Request> requestsByDepartment(int managerId) {
		
		List<Request> list = new ArrayList<>();
		try {
			Session s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			Query<Request> query = s.createQuery("FROM com.web.model.Request R WHERE R.managerid = :managerid", Request.class)
					.setParameter("managerid", managerId);
			list = query.list();

			tx.commit();
			return list;

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Request> requestsByEmployeeId(int employeeId) {
		
		List<Request> list = new ArrayList<>();
		try {
			Session s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			Query<Request> query = s
					.createQuery("FROM com.web.model.Request R WHERE R.employeeid = :employeeid",
							Request.class)
					.setParameter("employeeid", employeeId);
			list = query.list();

			tx.commit();
			return list;

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
		
	}

	@Override
	public List<Employee> allEmployees() {
		
		List<Employee> list = new ArrayList<>();
		
		try {
			Session s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			Query<Employee> query = s.createQuery("FROM com.web.model.Employee E", Employee.class);
			list = query.list();

			tx.commit();
			return list;

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Employee> assignedEmployee(int departmentId) {
		List<Employee> list = new ArrayList<>();
		try {
			Session s = HibernateSessionFactory.getSession();
			Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

			Query<Employee> query = s.createQuery("FROM com.web.model.Employee E WHERE E.departmentid = :departmentid", Employee.class)
					.setParameter("departmentid", departmentId);
			list = query.list();

			tx.commit();
			return list;

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void approveRequest(int requestId, int employeeId) {
		try {

			Session s = HibernateSessionFactory.getSession();
			s.beginTransaction();

			Request request = new Request();
			request.setRequestId(requestId);
			request.setStatus("Approved");
			request.setManagerId(employeeId);

			s.saveOrUpdate(request);
			s.getTransaction().commit();
			log.info("Completed Request Approval");
			return;

		} catch (HibernateException e) {
			e.printStackTrace();
			return;
		}

	}

	@Override
	public void denyRequest(int requestId, int employeeId) {
		
		try {

			Session s = HibernateSessionFactory.getSession();
			s.beginTransaction();

			Request request = new Request();
			request.setRequestId(requestId);
			request.setStatus("Rejected");
			request.setManagerId(employeeId);

			s.saveOrUpdate(request);
			s.getTransaction().commit();
			log.info("Rejected Request Complete");
			return;

		} catch (HibernateException e) {
			e.printStackTrace();
			return;
		}

	}

}
