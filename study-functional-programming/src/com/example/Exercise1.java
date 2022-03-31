package com.example;

import java.util.List;
import java.util.function.Predicate;

public class Exercise1 {

	public static void main(String[] args) {
		var students = List.of(
				new Student("1", "jack bauer",
						List.of(LetterGrade.AA, LetterGrade.FF, LetterGrade.CB, LetterGrade.BB, LetterGrade.CB)),
				new Student("2", "kate austen",
						List.of(LetterGrade.AA, LetterGrade.AA, LetterGrade.BB, LetterGrade.BA, LetterGrade.CB)),
				new Student("3", "james sawyer",
						List.of(LetterGrade.AA, LetterGrade.FF, LetterGrade.FF, LetterGrade.BA, LetterGrade.BA)),
				new Student("4", "jin kwon",
						List.of(LetterGrade.AA, LetterGrade.AA, LetterGrade.BB, LetterGrade.BB, LetterGrade.CC)));
        Predicate<Student> hasAtLeastOneFF = student -> student.grades().stream().anyMatch(LetterGrade.FF::equals);
		var failedStudents = students.stream().filter( hasAtLeastOneFF ).toList();
		System.out.println("""
				Failed Students
				===================
				""");
		failedStudents.forEach(System.out::println);
        System.out.println("""
        		Successful Students
        		===================
        		""");
        var successfulStudents = students.stream().filter( hasAtLeastOneFF.negate() ).toList();
        successfulStudents.forEach(System.out::println);
	}

}
