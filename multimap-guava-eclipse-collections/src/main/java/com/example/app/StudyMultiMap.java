package com.example.app;

import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

import com.example.domain.Employee;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class StudyMultiMap {

	public static void main(String[] args) {
		MutableListMultimap<String, Employee> employeesByLastName = Multimaps.mutable.list.with("austen",
				new Employee("1", "Kate", "Austen", 125_000.), "austen", new Employee("2", "Trisha", "Austen", 75_000.),
				"sawyer", new Employee("3", "Ethan", "Sawyer", 50_000.));
		System.out.println(employeesByLastName);
	}

}
