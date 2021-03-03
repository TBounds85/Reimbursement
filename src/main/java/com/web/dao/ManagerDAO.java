package com.web.dao;

import java.util.List;

import com.web.model.Employee;
import com.web.model.Request;

public interface ManagerDAO {
	List<Request> requestsByDepartment(int departmentId);
	List<Request> requestsByEmployeeId(int employeeIdint);
	List<Employee> allEmployees();
	List<Employee> assignedEmployee(int departmentId);
	void approveRequest(int requestId);
	void denyRequest(int requestId);
	
}
