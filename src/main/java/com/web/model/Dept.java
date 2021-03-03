package com.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "\"reimbursement\"")
public class Dept {

	@Id
	@Column
	int departmentId;

	@Column
	String departmentName;

	@Column
	int managerId;

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dept(int departmentId, String departmentName, int managerId) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + departmentId;
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + managerId;
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
		Dept other = (Dept) obj;
		if (departmentId != other.departmentId)
			return false;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		if (managerId != other.managerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dept [departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId=" + managerId
				+ "]";
	}

}