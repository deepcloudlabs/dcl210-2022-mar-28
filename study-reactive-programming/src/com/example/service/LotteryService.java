package com.example.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LotteryService {
	private static ExecutorService pool = Executors.newFixedThreadPool(50);
	public CompletableFuture<List<Integer>> draw(){
		return CompletableFuture.supplyAsync(() -> { //Higher-Order Function
			try {TimeUnit.SECONDS.sleep(10);}catch (Exception e) {}
			System.err.println(Thread.currentThread().getName()+": draw() logic is running...");
			return ThreadLocalRandom.current().ints(1,60).distinct()
					.limit(6).sorted().boxed().toList();
		},pool);
	}
}
