package com.web.dao;

import java.util.List;

import com.web.model.Employee;
import com.web.model.Request;

public interface ManagerDAO {
	List<Request> requestsByDepartment(int departmentId);
	List<Request> requestsByEmployeeId(int employeeId);
	List<Employee> allEmployees();
	List<Employee> assignedEmployee(int departmentId);
	void approveRequest(int requestId, int employeeId);
	void denyRequest(int requestId, int employeeId);
	List<Request> allResolvedRequests();
}
