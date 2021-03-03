package com.web.dao;

import java.util.List;

import com.web.model.Request;

public interface EmployeeDAO {
	
	boolean submitRequest(int employeeId);
	List<Request> pendingRequests(int employeeId);
	List<Request> resolvedRequests(int employeeId);
	void updateInformation(int employeeId);
	
}
