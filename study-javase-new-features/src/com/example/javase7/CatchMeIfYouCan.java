package com.example.javase7;

public class CatchMeIfYouCan {
	@SuppressWarnings("finally")
	public static int fun() {
		try {
			return 42;
		} finally {
			return 108;
		}
	}
	public static void main(String[] args) {
		System.err.println(fun());

	}

}
