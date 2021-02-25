package com.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "\"reimbursement\"")
public class Manager {

	@Id
	@Column
	int employeeId;
	@Column
	Dept department;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(int managerId, Dept department) {
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

	public Dept getDepartment() {
		return department;
	}

	public void setDepartment(Dept department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
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
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
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