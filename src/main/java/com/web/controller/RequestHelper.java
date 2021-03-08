package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.dao.EmployeeDAO;
import com.web.dao.LoginDAO;
import com.web.dao.ManagerDAO;
import com.web.dao.impl.EmployeeDAOImpl;
import com.web.dao.impl.LoginDAOImpl;
import com.web.dao.impl.ManagerDAOImpl;
import com.web.model.Employee;
import com.web.model.Request;
import com.web.service.FullService;
import com.web.service.FullServiceImpl;

public class RequestHelper {

	static Employee e;

	public static Object processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeDAO dao = new EmployeeDAOImpl();
		ManagerDAO mdao = new ManagerDAOImpl();
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/api/", "");
		HttpSession session = request.getSession();

		switch (RESOURCE) {

		case "pages/home":
			return e;

		case "pages/Mhome":
			return e;
		
		case "viewMyEmployees":
			int departmentId = (int) session.getAttribute("departmentId");
			List<Employee> list = mdao.assignedEmployee(departmentId);
			return list;
			
		case "viewAllEmployees":
			List<Employee> list1 = mdao.allEmployees();
			return list1;
		
		case "pages/decide":
			int managerId = e.getEmployeeId();
			List<Request> list3 = mdao.requestsByDepartment(managerId);
			return list3;

		case "pages/info":
			return e;

		case "pages/requestform":
			return e;

		case "pages/pending":
			int employeeId = (int) session.getAttribute("employeeId");
			List<Request> list4 = dao.pendingRequests(employeeId);
			return list4;

		case "pages/resolved":
			employeeId = (int) session.getAttribute("employeeId");
			List<Request> list2 = dao.resolvedRequests(employeeId);
			return list2;
			
		case "viewallresolved":
			//manager function view all resolved requests for all employees
			break;
			
		case "logout":
			session.invalidate();
			response.sendRedirect("/Reimbursement/index.html");
			break;

		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
			break;

		}
		return e;
		
	}

	public static Object processPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/api/", "");

		LoginDAO LV = new LoginDAOImpl();
		EmployeeDAO dao = new EmployeeDAOImpl();
		FullService FS = new FullServiceImpl();
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		switch (RESOURCE) {

		case "denyrequest":
			
			break;
			
		case "approverequest":
			
			break;
			
		case "editinfo":
			//data variables that don't change with update
			String firstName = (String) session.getAttribute("firstName");
			String lastName = (String) session.getAttribute("lastName");
			String dob = (String) session.getAttribute("dob");
			
			//editable variables
			String address = request.getParameter("addressinput");
			String city = request.getParameter("cityinput");
			String state = request.getParameter("stateinput");
			String zipcodestring = request.getParameter("zipinput");
			String contactstring = request.getParameter("contactinput");
			
			double contact;
			int zipcode;
			
//			//test to see variables after sent
//			System.out.println("BEFORE TRY/CATCHES raw input data\n");
//			System.out.println(firstName);
//			System.out.println(lastName);
//			System.out.println(dob);
//			System.out.println(contactstring);
//			System.out.println(address);
//			System.out.println(city);
//			System.out.println(state);
//			System.out.println(zipcodestring);
			
			
			try{
				zipcode = Integer.parseInt(zipcodestring);
			}catch (NumberFormatException e) {
				zipcode = (int) session.getAttribute("zip");
			}
			
			try{
				contact = Double.parseDouble(contactstring);
			}catch (NumberFormatException e) {
				contact = (double) session.getAttribute("contact");
			}
			
			if (address == "") {
				address = (String) session.getAttribute("address");
			}
			
			if (city == ""){
				city = (String) session.getAttribute("city");
			}
			
			if (state == "") {
				state = (String) session.getAttribute("state");
			}			
			
//			//test to see variables after parse/replacement
//			System.out.println("AFTER TRY/CATCHES/IF --- parsed/replaced data\n");
//			System.out.println(firstName);
//			System.out.println(lastName);
//			System.out.println(dob);
//			System.out.println(contact);
//			System.out.println(address);
//			System.out.println(city);
//			System.out.println(state);
//			System.out.println(zipcode);
						
			// update Employee Information
			dao.updateInformation(e.getEmployeeId(), firstName, lastName, dob ,contact, address, city, state, zipcode);

			// returns to Home Screen based on manager boolean
			if (e.isManager() == true) {
				response.sendRedirect("/Reimbursement/pages/Mhome.html");
				break;

			} else
				response.sendRedirect("/Reimbursement/pages/home.html");
				break;

		case "submitrequest":

			int employeeId = (int) session.getAttribute("employeeId");
			int managerId = (int) session.getAttribute("managerId");
			String reason = request.getParameter("reason");
			String amountString = request.getParameter("amount");
			double amount;

			try {
				amount = Double.parseDouble(amountString);
			} catch (NumberFormatException f) {
				f.printStackTrace();
				return e;
			}

			// future home of File Received Client ~> S3Bucket

			dao.submitRequest(employeeId, amount, reason, managerId);

			if (e.isManager() == true) {
				response.sendRedirect("/Reimbursement/pages/Mhome.html");
				break;

			} else
				response.sendRedirect("/Reimbursement/pages/home.html");
			break;

		case "login":

			final String USERNAME = request.getParameter("username");
			final String PASSWORD = request.getParameter("password");

			// instantiate LoginDAO interface implements IMPL

			// checks if password matches database and returns employeeId
			int checker = LV.validate(USERNAME, PASSWORD);

			if (checker != -1) {

				// set session attributes and return Complete Employee Object
				e = FS.setEmployeeToSession(request, response, checker);

				String json = new ObjectMapper().writeValueAsString(e);

				writer.write(json);

				if (e.isManager() == true) {
					response.sendRedirect("/Reimbursement/pages/Mhome.html");
					break;
				} else

					response.sendRedirect("/Reimbursement/pages/home.html");
				break;
			} else
				response.sendRedirect("/Reimbursement/invalid.html");
			break;

		case "logout":
			session.invalidate();
			response.sendRedirect("/Reimbursement/index.html");
			break;

		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
			break;
		}

		return e;

	}

}
