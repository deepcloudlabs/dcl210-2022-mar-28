package com.example.application;

import java.lang.reflect.Proxy;

import com.example.aspect.AuditingAspect;
import com.example.aspect.CachingAspect;
import com.example.aspect.ProfilingAspect;
import com.example.service.ArithmeticCalculator;
import com.example.service.business.StandardArithmeticCalculator;

public class CalculatorApp {

	public static void main(String[] args) {
		ArithmeticCalculator calculator = new StandardArithmeticCalculator();
		System.out.println(calculator.getClass());
		var clazz = calculator.getClass();
		calculator = (ArithmeticCalculator) 
				Proxy.newProxyInstance(
						clazz.getClassLoader(), 
						clazz.getInterfaces(), 
						new CachingAspect(calculator));		
		System.out.println(calculator.getClass());
		calculator = (ArithmeticCalculator) 
			Proxy.newProxyInstance(
				clazz.getClassLoader(), 
				clazz.getInterfaces(), 
				new AuditingAspect(calculator));
		System.out.println(calculator.getClass());
		calculator = (ArithmeticCalculator) 
				Proxy.newProxyInstance(
						clazz.getClassLoader(), 
						clazz.getInterfaces(), 
						new ProfilingAspect(calculator));
		System.out.println(calculator.getClass());
        System.out.println("2+5: "+calculator.add(2, 5));
        System.out.println("2+5: "+calculator.add(2, 5));
        System.out.println("2+5: "+calculator.add(2, 5));
        System.out.println("2-5: "+calculator.sub(2, 5));
        System.out.println("2*5: "+calculator.mul(2, 5));
        System.out.println("2/5: "+calculator.div(2, 5));
	}

}
