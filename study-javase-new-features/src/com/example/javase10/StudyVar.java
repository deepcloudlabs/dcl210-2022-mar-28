package com.example.javase10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public class StudyVar {

	public static void main(String[] args) {
		int x = 42;
		var y = 42; // y -> int
		Double i= 42.;
		var numbers = List.of(4,8,15,16,23,42); // var -> List<Integer>
		var numbers2 = List.of(4.,8,15,16,23,42); // var -> List<Number & Comparable<?> >
		var numbers3 = List.of(4,"eight",15,16,23,42); // var -> List<Object & Serializable & Comparable<?> >
		for (var number : numbers)  
			System.out.println(number);
		var v = fun();
		Map<String,Function<List<Integer>,String>> mappers = new HashMap<>();
		var mappersVarVersion = new HashMap<String,Function<List<Integer>,String>>();
		numbers3.get(0).compareTo(null);
		
	}

	static Map<String,Function<List<Integer>,String>> fun(){
		return null;
	}
}