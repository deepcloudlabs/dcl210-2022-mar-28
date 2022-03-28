package com.example.javase7;

import java.util.List;

@SuppressWarnings({"unused","removal"})
public class WrapperClasses {

	public static void main(String[] args) {
		int u = 42; // 4-byte
		Integer v = new Integer(42); // Object-Header (12-B) + 4B = 16B 
        double d = 1; // 8-byte
        Double dd = 1.; // 12B + 8B + 4B (alignment) = 24B
        List<Integer> numbers; // List<int> -> L1,L2 cache
		// -128..127
		// -Djava.lang.Integer.IntegerCache.high=4096
		// -2048..2047
		Integer x = Integer.valueOf(42);
		Integer y = 42;
		Integer m = 542;
		Integer n = 542;
		System.out.println("x==y? :"+(x==y)); // true
		System.out.println("m==n? :"+(m==n)); // false
		String name1 = "jack"; // static -> object pooling -> caching, immutable
		String name2 = new String("jack"); // dynamic
		String name3 = "jack";
		System.err.println("name1==name2? :"+(name1==name2));
		System.err.println("name1==name3? :"+(name1==name3));
		name3 = name3.toUpperCase();
		System.out.println(name3);
		// String, Integer, Character, Float, Double -> immutable class
		// BigDecimal, BigInteger, ...
		
	}

}

final class A { // immutable class -> object pooling
	private final int x;

	public A(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}
	
}