package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.dao.LoginDAO;
import com.web.dao.impl.LoginDAOImpl;
import com.web.model.Employee;
import com.web.model.Login;

public class RequestHelper {
	
	
	public static int processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameterNames());
		PrintWriter writer = response.getWriter();
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		
		switch(RESOURCE) {
			case "invalid/":
				writer.flush();
				break;
			
			case "api/logout/":
				writer.flush();
				System.out.println(request.getParameterNames()+" after flush");
				response.sendRedirect("/Reimbursement/index.html");
				break;
		
			default:
				
				response.setStatus(404);
				response.sendRedirect("/Reimbursement/404.html");
			
		
		}
		return 0;
	}
	public static int processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		
		switch(RESOURCE) {
		
		
		case "api/submitrequest":
			
			
			
			//grab submitted data if NOT a String (int, double, boolean, etc) have to parse
//			final int EMPLOYEEID = Integer.parseInt(request.getParameter("employeeid"));
			
			//grab Strings
//			final String FIRSTNAME = request.getParameter("firstname");
			break;
			
		case "api/login":
			
			final String USERNAME = request.getParameter("username");
			final String PASSWORD = request.getParameter("password");
			
			Login user = new Login();
			user.setUsername(USERNAME.toLowerCase());
			user.setPassword(PASSWORD);
			user.setEmployeeId(-1); //set to -1 to cause redirect of invalid credentials
			//instantiate LoginVerficationDAO interface implements IMPL
			LoginDAO  LV = new LoginDAOImpl();
			
			//checks if password matches database
			int checker = LV.validate(USERNAME, PASSWORD);
			
//			//sets EMPLOYEEID to employeeId# OR -1 if invalid login credentials 
//			request.setAttribute("EMPLOYEEID", checker);
			
			if(checker != -1) {	
				Employee e = LV.setupEmployee(checker);
				
				//set employee data to Attribute
//				request.setAttribute("EmployeeData", e);
				
				String json = new ObjectMapper().writeValueAsString(e);
				writer.write(json);		
						
						
				
				if(e.isManager() == true) {
					response.sendRedirect("/Reimbursement/pages/MHome.html");	
				}else
					response.sendRedirect("/Reimbursement/pages/home.html");
			}else 				
				response.sendRedirect("/Reimbursement/invalid.html");
			break;
		
		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
		}
		return 0;
		
	}

}


