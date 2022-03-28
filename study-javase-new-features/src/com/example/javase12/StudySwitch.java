package com.example.javase12;

public class StudySwitch {

	public static void main(String[] args) {
		int weekDay = 9;  
		String status;
		status = switch(weekDay) {
			 case 1,2,3,4,5 -> { yield "working"; }
			 case 6,7 -> { yield "resting"; }
			 default -> { yield "unknown"; }
		};				
		status = switch(weekDay) {
			case 1,2,3,4,5 -> "working";
			case 6,7 -> "resting"; 
			default -> "unknown"; 
		};				
		System.err.println(status);
	}

}
