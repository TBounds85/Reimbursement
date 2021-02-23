package com.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;


@Entity
@Table(name = "Login",schema = "\"reimbursement\"")
public class Login {

	
	@Id@Column(name="username")
	String username;
	
	@Column(name="password")
	String password;
	
	@Column(name="employeeid")
	@GeneratedValue(generator = "employee_id_seq",strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1,name = "employee_id_seq",initialValue = 1,sequenceName = "employee_id_seq" )
	int employeeId;
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Login(int employeeId, String username, String password) {
		super();
		this.employeeId = employeeId;
		this.username = username;
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Login other = (Login) obj;
		if (employeeId != other.employeeId)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", employeeId=" + employeeId + "]";
	}

//	@Override 	//used to return JUST THE password (have to use Getter for ID to return in IMPL if switching to this)
//	public String toString() {
//		return password;
//	}
	
}
