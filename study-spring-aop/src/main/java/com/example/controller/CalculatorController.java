package com.example.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aspect.Profiled;
import com.example.dto.request.CalculateRequest;
import com.example.service.ArithmeticCalculator;

@RestController
@RequestMapping("calculator")
@CrossOrigin
public class CalculatorController {
	private ArithmeticCalculator calculator;
	
	public CalculatorController(ArithmeticCalculator calculator) {
		System.err.println("CalculatorController");
		this.calculator = calculator;
	}

	@PostMapping
	@Profiled
	@Cacheable(cacheNames = "calculator")
	public double calculate(@RequestBody CalculateRequest request) {
		return switch (request.getOperation()) {
			case ADD -> { yield calculator.add(request.getLeft(), request.getRight()); }
			case SUB -> { yield calculator.sub(request.getLeft(), request.getRight()) ;}
			case MUL -> { yield calculator.mul(request.getLeft(), request.getRight()) ;}
			case DIV -> { yield calculator.div(request.getLeft(), request.getRight());} 
		} ;
	}
}
