package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	private static final Logger log=LogManager.getLogger(MyHttpServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = null;
		final String USERNAME = request.getParameter("username");
		final String PASSWORD = request.getParameter("password");

		if (USERNAME != null) {
			session = request.getSession();
		}

		if (session != null) {
			response.getWriter().write(RequestHelper.processGet(request, response));
		} else {
			response.getWriter().write("Client Not Authorized");
		}

		PrintWriter writer = response.getWriter();
		writer.write(RequestHelper.processGet(request, response)); // to use MyRequestHelper.class

//		/**
//		 * XML JSON (application/json) HTML (text/html) Plain Text (text/plain)
//		 */
//		response.setContentType("application/json"); // Javascript Object Notation
//
//		// creating new employee
//		Employee employee = new Employee(100, "Thomas", "Bounds", 123456789, "123 Test Lane", "Somewhere", "TX", 12345,
//				"1900-03-18", 1);
//
//		// creating List of employees
//		List<Employee> employees = new ArrayList<>();
//
//		// creating Object Mapper
//		ObjectMapper Om = new ObjectMapper();
//
//		// adding employee created to employee list
//		employees.add(employee);
//
//		// converting employee List to JSON
//		final String JSON = Om.writeValueAsString(employees);
//
//		// Printing JSON to Web Container
//		writer.write(JSON);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String USERNAME = request.getParameter("username");
		
		if (USERNAME != null) {
			Cookie Sugar = new Cookie("sessionId", "sessionValue");
			response.addCookie(Sugar);
		}
		
		Cookie desiredCookie = null;
		for(Cookie c : request.getCookies()) {
			if(c.getName().equals("sessionId") && c.getValue().equals("sessionValue")) {
				desiredCookie = c;
			}
		}
		if(desiredCookie != null) {
			RequestHelper.processPost(request, response);
		}else {
			response.getWriter().write("Client Not Authorized");
		}

//		PrintWriter writer = response.getWriter();
		RequestHelper.processPost(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}/************** END OF CLASS ************/
