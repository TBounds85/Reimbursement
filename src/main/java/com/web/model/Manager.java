package com.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "\"reimbursement\"")
public class Manager {

	@Id
	int employeeId;
	
	@Column
	int department;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(int managerId, int department) {
		super();
		this.employeeId = managerId;
		this.department = department;
	}

	public int getManagerId() {
		return employeeId;
	}

	public void setManagerId(int managerId) {
		this.employeeId = managerId;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + department;
		result = prime * result + employeeId;
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
		Manager other = (Manager) obj;
		if (department != other.department)
			return false;
		if (employeeId != other.employeeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manager [managerId=" + employeeId + ", department=" + department + "]";
	}

	
	
}