package com.example.javase14;

import java.util.Objects;

public class StudyIntanceOf {
	public static void main(String[] args) {
		Pet garfield = new Cat();
		if (garfield instanceof Cat) {
			var evcil = garfield;
			evcil.play();
		}
		if (garfield instanceof Spider) { // Guard
			var evcil1 = (Spider) garfield;
			if (evcil1 instanceof X) 
			   evcil1.toString();
			else
				evcil1.hashCode();
		}
		
		if (garfield instanceof Spider evcil && Objects.nonNull(evcil) && evcil instanceof X) {
			evcil.toString();
		}
		System.out.println(garfield instanceof Pet);
		
	}	
}

interface X {}
interface Pet {
	public default void play() {}
}
class Spider {}
class Cat implements Pet {}
class Fish implements Pet {}