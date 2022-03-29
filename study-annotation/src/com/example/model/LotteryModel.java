package com.example.model;

import java.util.List;

import com.example.service.Random;

public class LotteryModel {
	private List<Integer> numbers;

	public LotteryModel() {
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	@Random(max = 60, size = 6)
	public void setNumbers(List<Integer> numbers) {
		System.err.println("LotteryModel::setNumbers");
		this.numbers = numbers;
	}

}
