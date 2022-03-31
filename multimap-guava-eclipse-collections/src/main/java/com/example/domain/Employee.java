package com.example.domain;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Employee implements Comparable<Employee> {
	private String identityNo;
	private String firstName;
	private String lastName;
	private double salary;

	public Employee() {
	}

	public Employee(String identityNo, String firstName, String lastName, double salary) {
		this.identityNo = identityNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
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
		return "Employee [identityNo=" + identityNo + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + "]";
	}

	@Override
	public int compareTo(Employee other) {
		if (this.salary < other.salary)
			return -1;
		if (this.salary == other.salary)
			return 0;
		return 1;
	}

}
