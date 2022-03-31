package com.example.domain;

import java.math.BigDecimal;

import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbTransient;

public class Employee {
	private String identityNo;
	private String fullName;
	private String email;
	@JsonbTransient
	private String iban;
	@JsonbNumberFormat(locale = "tr_TR", value = "#0.0")
	private BigDecimal salary;

	public Employee() {
	}

	public Employee(String identityNo, String fullName, String email, String iban, BigDecimal salary) {
		this.identityNo = identityNo;
		this.fullName = fullName;
		this.email = email;
		this.iban = iban;
		this.salary = salary;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identityNo == null) ? 0 : identityNo.hashCode());
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
		if (identityNo == null) {
			if (other.identityNo != null)
				return false;
		} else if (!identityNo.equals(other.identityNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [identityNo=" + identityNo + ", fullName=" + fullName + ", email=" + email + ", iban=" + iban
				+ ", salary=" + salary + "]";
	}

}
