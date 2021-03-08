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
import com.web.dao.impl.EmployeeDAOImpl;
import com.web.dao.impl.LoginDAOImpl;
import com.web.model.Employee;
import com.web.model.Request;
import com.web.service.FullService;
import com.web.service.FullServiceImpl;

public class RequestHelper {

	static Employee e;

	public static Object processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeDAO dao = new EmployeeDAOImpl();
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Reimbursement/api/", "");
		HttpSession session = request.getSession();

		switch (RESOURCE) {

		case "pages/home":

			return e;

		case "pages/Mhome":

			return e;

		case "pages/decide":

			return e;

		case "pages/info":
			// done
			return e;

		case "pages/requestform":
			// done
			return e;

		case "pages/pending":
			int employeeId = (int) session.getAttribute("employeeId");
			List<Request> list = dao.pendingRequests(employeeId);
			return list;

		case "pages/resolved":
			employeeId = (int) session.getAttribute("employeeId");
			List<Request> list1 = dao.resolvedRequests(employeeId);
			return list1;

		case "api/logout":
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

		case "editinfo":
			System.out.println(request);
			System.out.println(response);
			double contact = Double.parseDouble(request.getParameter("contactinput"));
			String address = request.getParameter("addressinput");
			String city = request.getParameter("cityinput");
			String state = request.getParameter("stateinput");
			int zip = Integer.parseInt(request.getParameter("zipinput"));

			if (contact == 0) {
				contact = (double) session.getAttribute("contact");
			}
			if (address.equals("")) {
				address = (String) session.getAttribute("address");
			}
			if (city.equals("")) {
				city = (String) session.getAttribute("city");
			}
			if (state.equals("")) {
				state = (String) session.getAttribute("state");
			}
			if (zip == 0) {
				zip = (int) session.getAttribute("zip");
			}

			// update Employee Information
			dao.updateInformation(e.getEmployeeId(), contact, address, city, state, zip);

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
