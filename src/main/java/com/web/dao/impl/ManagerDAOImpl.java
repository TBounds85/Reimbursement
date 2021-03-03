package com.web.dao.impl;

import java.util.List;

import com.web.dao.ManagerDAO;
import com.web.model.Employee;
import com.web.model.Request;

public class ManagerDAOImpl implements ManagerDAO {

	@Override
	public List<Request> requestsByDepartment(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> requestsByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> allEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> assignedEmployee(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approveRequest(int requestId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void denyRequest(int requestId) {
		// TODO Auto-generated method stub

	}

}
