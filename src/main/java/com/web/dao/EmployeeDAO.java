package com.web.dao;

import java.util.List;

import com.web.model.Request;

public interface EmployeeDAO {
	
	
	List<Request> pendingRequests(int employeeId);
	List<Request> resolvedRequests(int employeeId);
	boolean submitRequest(int employeeId, double requestedAmount, String reason, int managerId);
	void updateInformation(int employeeId, String firstName, String lastName, String dob, double contact, String address, String city, String state, int zipcode);
	
}
