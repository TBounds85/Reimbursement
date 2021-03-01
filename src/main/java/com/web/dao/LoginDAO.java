package com.web.dao;

import com.web.model.Employee;

public interface LoginDAO {

	int validate(String username, String password);
	Employee setupEmployee(int employeeId);
	
}