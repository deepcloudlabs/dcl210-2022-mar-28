package com.example.service.business;

import com.example.service.ArithmeticCalculator;

public class StandardArithmeticCalculator implements ArithmeticCalculator {

	@Override
	public double add(double x, double y) {
		System.out.println("StandardArithmeticCalculator::add");
		return x+y;
	}

	@Override
	public double sub(double x, double y) {
		return x-y;
	}

	@Override
	public double mul(double x, double y) {
		return x*y;
	}

	@Override
	public double div(double x, double y) {
		return x/y;
	}

}
