package com.example.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.domain.Employee;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class StudyMultiMapInJavaSE {

	public static void main(String[] args) {
		Map<String, List<Employee>> multimap = new HashMap<>();
		put(multimap, "austen", new Employee("1", "Kate", "Austen", 125_000.));
		put(multimap, "austen", new Employee("2", "Kate", "Austen", 125_000.));
		put(multimap, "sawyer", new Employee("3", "James", "Sawyer", 125_000.));
	}

	public static <K, V> void put(Map<K, List<V>> map, K key, V employee) {
		List<V> emps = map.get(key);
		if (emps == null) {
			emps = new ArrayList<>();
			map.put(key, emps);
		}
		emps.add(employee);
	}
}
