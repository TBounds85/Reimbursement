package com.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Requests", schema = "\"reimbursement\"")
public class Request {

	@Id
	@Column
	@GeneratedValue(generator = "request_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "request_id_seq", initialValue = 1, sequenceName = "request_id_seq")
	int requestId;

	@Column
	int employeeid;

	@Column
	double requestedAmount;

	@Column
	String reason;

	@Column
	String status;

	@Column
	int managerId;

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(int requestId, int employeeid, double requestedAmount, String reason, String status, int managerId) {
		super();
		this.requestId = requestId;
		this.employeeid = employeeid;
		this.requestedAmount = requestedAmount;
		this.reason = reason;
		this.status = status;
		this.managerId = managerId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public double getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		result = prime * result + employeeid;
		result = prime * result + managerId;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + requestId;
		long temp;
		temp = Double.doubleToLongBits(requestedAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Request other = (Request) obj;
		if (employeeid != other.employeeid)
			return false;
		if (managerId != other.managerId)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (requestId != other.requestId)
			return false;
		if (Double.doubleToLongBits(requestedAmount) != Double.doubleToLongBits(other.requestedAmount))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", employeeid=" + employeeid + ", requestedAmount=" + requestedAmount
				+ ", reason=" + reason + ", status=" + status + ", managerId=" + managerId + "]";
	}

}
