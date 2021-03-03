package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.dao.LoginDAO;
import com.web.dao.impl.LoginDAOImpl;
import com.web.model.Employee;

public class RequestHelper {
	
	
	public static int processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		HttpSession session = request.getSession(false);
		switch(RESOURCE) {
			case "pages/home":
				session = request.getSession();
				
				
				Object e = session.getAttributeNames();
				
				System.out.println(e);
				break;
				
			case "pages/Mhome":
				
				break;
				
			case "pages/info":
				
				break;
				
			case "pages/requestform":
				
				break;
				
			case "pages/pending":
				
				break;
				
			case "pages/resolved":
				
				break;
				
			case "invalid":
				
				break;
			
			case "api/logout/":
				
				response.sendRedirect("/Reimbursement/index.html");
				break;
		
			default:
				
				response.setStatus(404);
				response.sendRedirect("/Reimbursement/404.html");
			break;
		
		}
		return 0;
	}
	public static int processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		
		HttpSession session = request.getSession(false);
		
		switch(RESOURCE) {
		
		case "pages/info":
			
			break;
			
		case "pages/requestform":
		
			break;
			

		
		case "api/editInfo":
			break;
			
		case "api/logout":
			session.invalidate();
			break;
			
		case "api/submitrequest":
			
			
			
			//grab submitted data if NOT a String (int, double, boolean, etc) have to parse
//			final int EMPLOYEEID = Integer.parseInt(request.getParameter("employeeid"));
			
			//grab Strings
//			final String FIRSTNAME = request.getParameter("firstname");
			break;
			
		case "api/login":
			
			
			final String USERNAME = request.getParameter("username");
			final String PASSWORD = request.getParameter("password");
			
			//instantiate LoginDAO interface implements IMPL
			LoginDAO  LV = new LoginDAOImpl();
			
			//checks if password matches database
			int checker = LV.validate(USERNAME, PASSWORD);
			 
			if(checker != -1) {	
				
				//get session create Employee Object with fields
				session = request.getSession();
				Employee e = LV.setupEmployee(checker);
				
				//set employee data to Attribute
//				request.setAttribute("EmployeeData", e);
				session.setAttribute("EmployeeData", e);
				String json = new ObjectMapper().writeValueAsString(e);
				
				writer.write(json);	
				
				if(e.isManager() == true) { 
					response.sendRedirect("/Reimbursement/pages/Mhome.html");	
					break;
				}else
					
					response.sendRedirect("/Reimbursement/pages/home.html");
					break;
			}else 
				response.sendRedirect("/Reimbursement/invalid.html");
			break;
		
		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
			break;
		}
		return 0;
		
	}

}


