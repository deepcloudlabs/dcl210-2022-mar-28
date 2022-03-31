package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Exercise1 {

	public static void main(String[] args) {
		// I. Functional Requirements
		// i. duplicate ii. ordered -> can sort
		List<Integer> numbers = new ArrayList<>();
		numbers.add(42);
		numbers.add(42);
		numbers.add(42);
		System.out.println(numbers);
		numbers.add(0, 8);
		System.out.println(numbers);
		System.out.println(numbers.get(1));
		numbers.sort(Integer::compare);
		System.out.println(numbers.contains(108));
		Comparator<Employee> orderBySalaryDesc = (emp1,emp2) -> {
			if (emp1.getSalary() > emp2.getSalary()) return -1;
			return +1;
		};
		// i. unique ii. unordered -> cannot sort
		Set<Employee> employees = new TreeSet<>(orderBySalaryDesc.reversed());
		employees.add(new Employee("1", "jack bauer",50_000));
		employees.add(new Employee("2", "kate austen",70_000));
		employees.add(new Employee("3", "james sawyer",60_000));
		employees.add(new Employee("4", "jin kwon",10_000));
		employees.add(new Employee("5", "sun kwon",20_000));
		employees.add(new Employee("6", "ben linus",30_000));
		System.out.println(employees.size());
		System.out.println(employees.contains(new Employee("1", "jack bauer",20_000)));
		employees.forEach(System.err::println);
		// III. Map i. (Key->Value)
		var areaCodes = new HashMap<String,Integer>();
		areaCodes.put("istanbul.anadolu", 216);
		areaCodes.put("istanbul.avrupa", 212);
		areaCodes.put("ankara", 312);
		System.out.println(areaCodes.get("ankara"));
		var quiz = new TreeMap<Integer,String>();
		quiz.put(42, "forty-two"); // 1
		quiz.put(42, "forty-two"); // 1
		quiz.put(549, "five-hundred-and-forty-nine"); // 2
		quiz.put(549, "five-hundred-and-forty-nine"); // 3
		quiz.put(560, "five-hundred-and-forty-nine"); // 4
		quiz.put(560, "five-hundred-and-forty-nine"); // 5
		quiz.put(60, "five-hundred-and-forty-nine"); // 6
		quiz.put(60, "five-hundred-and-forty-nine"); // 6
		quiz.put(60, "five-hundred-and-forty-nine"); // 6
		quiz.put(60, "five-hundred-and-forty-nine"); // 6
		quiz.put(60, "five-hundred-and-forty-nine"); // 6
		quiz.put(Integer.valueOf(60), "five-hundred-and-forty-nine"); // 6
		System.out.println(quiz);
	}

}

class Employee implements Comparable<Employee>{
	private String identity;
	private String fullname;
	private double salary;
	
	public Employee() {
	}

	public Employee(String identity, String fullname,double salary) {
		this.identity = identity;
		this.fullname = fullname;
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFullname() {
		return fullname;
	} 

	public void setFullname(String fullname) {
		this.fullname = fullname;
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
		return "Employee [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + "]";
	}

	@Override
	public int compareTo(Employee other) {
		return this.fullname.compareTo(other.fullname);
	}

}