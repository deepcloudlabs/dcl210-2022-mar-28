package com.example;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class Introduction {

	public static void main(String[] args) {
		var students = List.of(
				new Student("1", "jack bauer",
						List.of(LetterGrade.AA, LetterGrade.FF, LetterGrade.CB, LetterGrade.BB, LetterGrade.CB)),
				new Student("2", "kate austen",
						List.of(LetterGrade.AA, LetterGrade.AA, LetterGrade.BB, LetterGrade.BA, LetterGrade.CB)),
				new Student("3", "james sawyer",
						List.of(LetterGrade.AA, LetterGrade.FF, LetterGrade.FF, LetterGrade.BA, LetterGrade.BA)),
				new Student("4", "jin kwon",
						List.of(LetterGrade.AA, LetterGrade.AA, LetterGrade.BB, LetterGrade.BB, LetterGrade.FF)));
		// Outer Loop
		for (var student : students) {
			var averageGrade = calculateAverageGrade(student);
			System.out.println("%11s %16s %2.1f".formatted(
					student.identity(), student.fullname(), averageGrade));
		}
		// Functional Programming + Stream API -> Collection: Filter/Map/Reduce
		// Inner Loop
		students.stream()
		        .parallel() // Fork-Join Framework (Java SE 7)
		        .map(student -> {
					var avgGrade = student.grades().stream().mapToInt(LetterGrade::getValue).average();
					return new StudentGrade(student.fullname(), avgGrade.getAsDouble());
				})
		        .forEach(studentGrade -> 
					System.err.println(studentGrade.fullname()+": "+studentGrade.grade())
		        );
		        
	}

	private static double calculateAverageGrade(Student student) {
		return getGradeSum(student) / student.grades().size();
	}

	private static double getGradeSum(Student student) {
		var averageGrade = 0.0;
		for (var grade : student.grades()) {
			averageGrade += grade.getValue();
		}
		return averageGrade;
	}

}

enum LetterGrade {
	AA(10), BA(9), BB(8), CB(7), CC(6), DC(5), DD(4), FF(0);

	private final int value;

	private LetterGrade(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
record StudentGrade(String fullname,double grade) {}
record Student(String identity, String fullname, List<LetterGrade> grades) {
}