package com.example;

public class Exercise2 {

	public static void main(String[] args) {
		int x= 42; // 4-Byte
		int y= 42;
		System.out.println("x==y ? "+(x==y));
		A u = new A(); // reference variable
		A v = new A();
		System.out.println("u==v ? "+(u==v));
		Integer m= Integer.valueOf(42); // reference variable
		Integer n= 42; // 12B + 4B = 16B
		System.out.println("m==n ? "+(m==n));
		Integer p= new Integer(549); // reference variable
		Integer q= new Integer(549);
		System.out.println("p==q ? "+(p==q));
	}

}

class A {}