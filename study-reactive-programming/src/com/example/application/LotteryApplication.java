package com.example.application;

import java.util.concurrent.TimeUnit;

import com.example.service.LotteryService;

public class LotteryApplication {

	public static void main(String[] args) {
		var lotteryService = new LotteryService();
		System.out.println(Thread.currentThread().getName()+": Waiting for the response from the LotteryService...");
		for (var i=0;i<2000;++i)
			lotteryService.draw()
			              .thenAccept( numbers -> 
			            	  System.err.println(Thread.currentThread().getName()+": "+numbers)
			              ); // Higher-Order Function
		System.out.println(Thread.currentThread().getName()+": Application is done.");
		try {TimeUnit.SECONDS.sleep(1000);}catch (Exception e) {}
	}

}
