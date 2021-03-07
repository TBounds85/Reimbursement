package com.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.web.model.Dept;
import com.web.model.Employee;
import com.web.model.EmployeeDetails;
import com.web.util.HibernateSessionFactory;

public class FullServiceImpl implements FullService {
	
	static Employee e;
	Logger log = Logger.getLogger(FullServiceImpl.class);
	
	@Override
	public Employee setEmployeeToSession(HttpServletRequest request, HttpServletResponse response, int employeeId){
		
			HttpSession hs = request.getSession();
			
			try{
				Session s = HibernateSessionFactory.getSession();
				Transaction tx = s.beginTransaction(); // sets tx as query+beginTransaction

				// storing result <~~ of the query
				e = s.createQuery("FROM com.web.model.Employee L WHERE L.employeeId = :employeeId", Employee.class)
						.setParameter("employeeId", employeeId).getSingleResult();
				
				Dept d = (Dept) s.createQuery("FROM com.web.model.Dept D WHERE D.departmentId = :departmentId", Dept.class)
						.setParameter("departmentId", e.getDepartment().getDepartmentId()).getSingleResult();

				EmployeeDetails ed = (EmployeeDetails) s.createQuery("FROM com.web.model.EmployeeDetails f WHERE f.employeeId = :employeeId", EmployeeDetails.class)
						.setParameter("employeeId", e.getInfo().getEmployeeId()).getSingleResult();

				tx.commit();// commits query (Data Now Available for parsing)
				
				//Set up session attributes
				hs.setAttribute("EmployeeData", e);
				hs.setAttribute("manager", e.isManager());
				hs.setAttribute("employeeId", e.getEmployeeId());
				hs.setAttribute("departmentInfo", e.getDepartment());
				hs.setAttribute("employeeInfo", e.getInfo());
				
				hs.setAttribute("firstName", ed.getFirstName());
				hs.setAttribute("lastName", ed.getLastName());
				hs.setAttribute("dob", ed.getDob());
				hs.setAttribute("contact", ed.getContact());
				hs.setAttribute("address", ed.getAddress());
				hs.setAttribute("city", ed.getCity());
				hs.setAttribute("state", ed.getState());
				hs.setAttribute("zip", ed.getZipcode());
				
				hs.setAttribute("departmentId", d.getDepartmentId());
				hs.setAttribute("departmentName", d.getDepartmentName());
				hs.setAttribute("managerId", d.getManagerId());
				
				if(hs.getAttribute("managerId").equals(hs.getAttribute("employeeId"))){
					hs.setAttribute("managerId", 999);
				}
				
				}catch (HibernateException f) {
					f.printStackTrace();
					log.error("Hibernate error in setupEmployee()");				
				}
			return e;
		}
	
}
