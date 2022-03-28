package com.example.javase14;

import java.util.Objects;

// class
// enum
// annotation
// interface
public class StudyRecord {
	// Lombok 
	public static void main(String[] args) {
		var calisan1 = new Calisan("1", "kate austen", "kate@example.com", 100000, "tr1");
		var employee1 = new Employee("1", "kate austen", "kate@example.com", 100000, "tr1");
		System.out.println(calisan1);
		System.out.println(calisan1.fullname());
		System.out.println(employee1);
		System.out.println(employee1.getFullname());
	}
}

// List<Integer>
record Calisan(String identity, String fullname, String email, double salary, String iban) {

}

final class Employee { // immutable
	private final String identity;
	private final String fullname;
	private final String email;
	private final double salary;
	private final String iban;

	public Employee(String identity, String fullname, String email, double salary, String iban) {
		this.identity = identity;
		this.fullname = fullname;
		this.email = email;
		this.salary = salary;
		this.iban = iban;
	}

	public String getIdentity() {
		return identity;
	}

	public String getFullname() {
		return fullname;
	}

	public String getEmail() {
		return email;
	}

	public double getSalary() {
		return salary;
	}

	public String getIban() {
		return iban;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
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
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullname=" + fullname + ", email=" + email + ", salary=" + salary
				+ ", iban=" + iban + "]";
	}

}