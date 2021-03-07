package com.web.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.model.Employee;

public interface FullService {

	Employee setEmployeeToSession(HttpServletRequest request, HttpServletResponse response, int employeeId);

}
