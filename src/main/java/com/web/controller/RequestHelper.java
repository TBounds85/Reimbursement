package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.dao.EmployeeDAO;
import com.web.dao.LoginDAO;
import com.web.dao.impl.EmployeeDAOImpl;
import com.web.dao.impl.LoginDAOImpl;
import com.web.model.Employee;
import com.web.service.FullService;
import com.web.service.FullServiceImpl;

public class RequestHelper {
	
	
	static Employee e;
	public static Object processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/api/", "");
		HttpSession session = request.getSession();
		FullService FS = new FullServiceImpl();
		
		switch(RESOURCE) {
			
			case "pages/home":
				
				
				return e;
				
			case "pages/Mhome":
				
				int employeeId1 = (int) session.getAttribute("employeeId");
				
				e = FS.setEmployeeToSession(request, response, employeeId1);
				
				return e;
				
			case "pages/decide":
				
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
			
			case "api/logout":
				session.invalidate();
				response.sendRedirect("/Reimbursement/index.html");
				break;
		
			default:
				
				response.setStatus(404);
				response.sendRedirect("/Reimbursement/404.html");
			break;
		
		}
		return 0;
	}
	public static Object processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/api/", "");
		
		
		EmployeeDAO dao =new EmployeeDAOImpl();
		FullService FS = new FullServiceImpl();
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		switch(RESOURCE) {
				
			case "pages/info":
			
				break;
			
			case "pages/requestform":
			
				break;
		
			case "editInfo":
			
				break;
			
			case "submitrequest":
			
				int employeeId = (int) session.getAttribute("employeeId");
				int managerId =  (int) session.getAttribute("managerId");
				double amount = Double.parseDouble(request.getParameter("amount"));
				String reason = request.getParameter("reason");
			
				//future home of File Received Client ~> S3Bucket
			
				dao.submitRequest(employeeId, amount, reason, managerId);
			
				break;
			
		case "login":
			
			
			final String USERNAME = request.getParameter("username");
			final String PASSWORD = request.getParameter("password");
			
			//instantiate LoginDAO interface implements IMPL
			LoginDAO  LV = new LoginDAOImpl();
			
			//checks if password matches database
			int checker = LV.validate(USERNAME, PASSWORD);
			session.setAttribute("employeeId", checker);
			
			if(checker != -1) {	
				
				//get session create Employee Object with fields
				e = FS.setEmployeeToSession(request, response, checker);
				
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
		
		case "logout":
			session.invalidate();
			break;
			
		default:
			response.setStatus(404);
			response.sendRedirect("/Reimbursement/404.html");
			break;
		}
		
		
		return e;
		
	}

}


