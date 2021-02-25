package com.web.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class EmployeeDetails {
	
	@Id
	@Column
	int employeeId;
	
	@Column
	String firstName;
	
	@Column
	String lastName;
	
	@Column
	double contact;
	
	@Column
	String address;
	
	@Column
	String city;
	
	@Column
	String state;
	
	@Column
	int zipcode;
	
	@Column
	String dob;

	public EmployeeDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
