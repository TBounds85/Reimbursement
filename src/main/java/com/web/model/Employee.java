package com.web.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "\"reimbursement\"")
public class Employee {

	@Id
	@Column
	@GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "employee_id_seq", initialValue = 1, sequenceName = "employee_id_seq")
	int employeeId;

//join with department table
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "EmployeeDetails_FK")
	private EmployeeDetails info;

	// join with department table
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_FK")
	private Dept department;

	// join with Login records
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "loginInfo_FK")
	private Login loginInfo;

	@Column
	boolean manager;

//	Constructors
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}








}
	