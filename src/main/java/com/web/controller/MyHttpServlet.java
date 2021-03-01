package com.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	private static final Logger log=LogManager.getLogger(MyHttpServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		final String USERNAME = request.getParameter("username");
//		final String PASSWORD = request.getParameter("password");

		HttpSession session = null;
		if (USERNAME != null) {
			session = request.getSession();
		}

		if (session != null) {
			String json = new ObjectMapper().writeValueAsString(RequestHelper.processPost(request, response));
			writer.write(json);
//			writer.write(RequestHelper.processGet(request, response)); //not in json Wrapper ~~ to use MyRequestHelper.class
		} else {
			writer.write("Client Not Authorized");
		}

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		final String USERNAME = request.getParameter("username");

		if (USERNAME != null) {
			
			//Generate/implement cookie
//			Cookie GingerSnap = new Cookie(USERNAME, USERNAME);
//			

			// converts RequestHelper class as json string
			String json = new ObjectMapper().writeValueAsString(RequestHelper.processPost(request, response)); // to use MyRequestHelper.class
			
			// writes response in json
			writer.write(json);
		} else {
			writer.write("YOU KNOW YOU CANT DO THAT!!!! Login Like You're Supposed To.");
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}/************** END OF CLASS ************/
