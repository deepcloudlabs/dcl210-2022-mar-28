package com.example.app;

import com.example.domain.Employee;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class StudyGuavaMultiMap {

	public static void main(String[] args) {
		Multimap<String, Employee> employees = ArrayListMultimap.create();
		employees.put("austen", new Employee("1", "Kate", "Austen", 125_000.));
		employees.put("austen", new Employee("2", "Kate", "Austen", 125_000.));
		employees.put("sawyer", new Employee("3", "Ethan", "Sawyer", 50_000.));
		System.out.println(employees);
		Multimap<String, Integer> areaCodes = ArrayListMultimap.create();
		areaCodes.put("istanbul", 212);
		areaCodes.put("istanbul", 216);
		System.out.println(areaCodes);
	}

}
