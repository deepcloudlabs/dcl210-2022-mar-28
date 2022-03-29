package com.example.application;

import java.lang.reflect.InvocationTargetException;

import com.example.model.LotteryModel;
import com.example.service.RandomNumberGenerator;

public class LotteryApplication {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		var lotteryModel = new LotteryModel();
		RandomNumberGenerator.populate(lotteryModel);
		System.out.println(lotteryModel.getNumbers());
	}

}
