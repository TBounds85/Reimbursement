package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.LoginVerificationDAO;
import com.web.dao.impl.LoginVerificationDAOImpl;

public class RequestHelper {

	
	public static int processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI();
		System.out.println(URI);
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		
		switch(RESOURCE) {
		case "api/logout/":
			response.sendRedirect("/Reimbursement/index.html");
		
		break;
		
		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
			
		
		}
		return 0;
	}
	public static int processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI();
		System.out.println(URI);
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		
		switch(RESOURCE) {
		case "api/addnewemployee":
			
			//grab submitted data if NOT a String (int, double, boolean, etc) have to parse
//			final int EMPLOYEEID = Integer.parseInt(request.getParameter("employeeid"));
			
			//grab Strings
//			final String FIRSTNAME = request.getParameter("firstname");
			break;
			
		case "api/login":
			
			final String USERNAME = request.getParameter("username");
			final String PASSWORD = request.getParameter("password");
			
			//instantiate LoginVerficationDAO interface implements IMPL
			LoginVerificationDAO  LV = new LoginVerificationDAOImpl();
			
			//checks if password matches database
			Boolean checker = LV.validate(USERNAME, PASSWORD);
			
			if(checker == true) {
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


