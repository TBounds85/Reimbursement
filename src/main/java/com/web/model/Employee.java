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

	@Column
	boolean manager;

//	Constructors
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

public Employee(int employeeId, EmployeeDetails info, Dept department, boolean manager) {
	super();
	this.employeeId = employeeId;
	this.info = info;
	this.department = department;
	this.manager = manager;
}

public int getEmployeeId() {
	return employeeId;
}

public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
}

public EmployeeDetails getInfo() {
	return info;
}

public void setInfo(EmployeeDetails info) {
	this.info = info;
}

public Dept getDepartment() {
	return department;
}

public void setDepartment(Dept department) {
	this.department = department;
}

public boolean isManager() {
	return manager;
}

public void setManager(boolean manager) {
	this.manager = manager;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((department == null) ? 0 : department.hashCode());
	result = prime * result + employeeId;
	result = prime * result + ((info == null) ? 0 : info.hashCode());
	result = prime * result + (manager ? 1231 : 1237);
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Employee other = (Employee) obj;
	if (department == null) {
		if (other.department != null)
			return false;
	} else if (!department.equals(other.department))
		return false;
	if (employeeId != other.employeeId)
		return false;
	if (info == null) {
		if (other.info != null)
			return false;
	} else if (!info.equals(other.info))
		return false;
	if (manager != other.manager)
		return false;
	return true;
}

@Override
public String toString() {
	return "Employee [employeeId=" + employeeId + ", info=" + info + ", department=" + department + ", manager="
			+ manager + "]";
}
	
}