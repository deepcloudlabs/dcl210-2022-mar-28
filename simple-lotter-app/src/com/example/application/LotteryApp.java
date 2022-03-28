package com.example.application;

import java.util.concurrent.ThreadLocalRandom;

public class LotteryApp {

	public static void main(String[] args) {
		var numbers = ThreadLocalRandom.current()
				        .ints(1, 60)
				        .distinct()
				        .limit(6)
				        .sorted()
				        .boxed()
				        .toList();
		System.out.println(numbers);
	}

}
