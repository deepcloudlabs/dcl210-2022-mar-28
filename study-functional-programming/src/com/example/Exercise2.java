package com.example;

import static java.lang.System.out;
import static java.util.Comparator.comparing;

import java.util.List;
import java.util.function.Function;

public class Exercise2 {

	public static void main(String[] args) {
		var students = List.of(
				new Student("1", "jack bauer",
						List.of(LetterGrade.AA, LetterGrade.FF, LetterGrade.CB, LetterGrade.BB, LetterGrade.CB)),
				new Student("2", "kate austen",
						List.of(LetterGrade.AA, LetterGrade.AA, LetterGrade.BB, LetterGrade.AA, LetterGrade.CB)),
				new Student("3", "james sawyer",
						List.of(LetterGrade.AA, LetterGrade.FF, LetterGrade.FF, LetterGrade.BA, LetterGrade.BA)),
				new Student("4", "jin kwon",
						List.of(LetterGrade.AA, LetterGrade.AA, LetterGrade.AA, LetterGrade.BA, LetterGrade.BA)));
		Function<Student,Long> extractNumberOfAAs = 
				student -> student.grades().stream().filter(LetterGrade.AA::equals).count();
		Function<Student,Double> extractAverageGrade = 
				student -> student.grades().stream().mapToInt(LetterGrade::getValue).average().getAsDouble();
		// max, ifPresent, mapToInt : higher-order function
		// 	extractNumberOfAAs, extractAverageGrade: pure function -> immutability	
		students.stream()
		        .parallel() 
		        .max(comparing(extractNumberOfAAs).thenComparing(extractAverageGrade))
		        .ifPresent(out::println);
	}

}
