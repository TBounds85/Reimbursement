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


		final String USERNAME = request.getParameter("username");
//		final String PASSWORD = request.getParameter("password");

		HttpSession session = null;
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
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String USERNAME = request.getParameter("username");
//		final String PASSWORD = request.getParameter("password");
		
		if (USERNAME != null) {
			
			RequestHelper.processPost(request, response);
		}else {
			response.getWriter().write("YOU KNOW YOU CANT DO THAT!!!! Login Like You're Supposed To.");
		}

//		PrintWriter writer =  response.getWriter();
//		writer.write(RequestHelper.processPost(request, response)); // to use MyRequestHelper.class
		
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}/************** END OF CLASS ************/
