package com.example.exercise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Exercise2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		var threadPool = Executors.newFixedThreadPool(10*Runtime.getRuntime().availableProcessors());
		threadPool.execute(()->{
			System.err.println("Hello World");
		});
		
		var future = threadPool.submit(() ->{
			return 42;
		});
		System.out.println(future.get());
		//Executors.newFixedThreadPool(0)

	}

}
