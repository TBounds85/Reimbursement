package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.dao.LoginVerificationDAO;

public class RequestHelper {

	
	public static int processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		
		switch(RESOURCE) {
//		case "":
//			response.sendRedirect("/Reimbursement/pages/home.html");
		
		
		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
			
		
		}
		return 0;
	}
	public static void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/api/", "");
		
		switch(RESOURCE) {
		case "newemployee":
			
			//grab submitted data if NOT a String (int, double, boolean, etc) have to parse
//			final int EMPLOYEEID = Integer.parseInt(request.getParameter("employeeid"));
			
			//grab Strings
			final String FIRSTNAME = request.getParameter("firstname");
			break;
			
		case "login":
			final String USERNAME = request.getParameter("username");
			final String PASSWORD = request.getParameter("password");
			//if password matches database
			if(LoginVerificationDAO.validate(USERNAME,PASSWORD) == true) {
				response.sendRedirect("/Reimbursement/pages/home.html");
			}else 
				response.sendRedirect("/Reimbursement/invalid.html");
			
		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
		}
		
	}

}


