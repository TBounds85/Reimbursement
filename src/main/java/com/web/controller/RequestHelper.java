package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	
	public static String processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		
		switch(RESOURCE) {
		case "login":
			
			return " ";
		
		default:
			
			return "";
		
		}
	}
	public static void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/", "");
		
		switch(RESOURCE) {
		case "newemployee":
			
			//grab submitted data if NOT a String (int, double, boolean, etc) have to parse
			final int employeeId = Integer.parseInt(request.getParameter("employeeid"));
			
			//grab Strings
			final String firstName = request.getParameter("firstname");
			break;
			
		case "login":
			
			
		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
		}
		
	}

}


