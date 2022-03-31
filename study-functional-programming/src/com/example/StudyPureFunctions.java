package com.example;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class StudyPureFunctions {

	public static void main(String[] args) {
		var numbers = List.of(4,8,15,16,23,42);
		final int localState = 108;
		Predicate<Integer> ifEven = n -> { var x= localState; return n%2 == 0;};
		ToDoubleFunction<Integer> toCube = x -> x*x*x;
		var solution =
		numbers.stream()
		       .parallel()
		       .filter(ifEven) // higher-order function
		       .mapToDouble(toCube) // higher-order function
		       .sum();
		System.out.println(solution);
	}

}
