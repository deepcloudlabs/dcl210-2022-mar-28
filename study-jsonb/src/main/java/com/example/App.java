package com.example;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;

import com.example.domain.Employee;

public class App {
	public static void main(String[] args) {
		JsonbConfig config = 
			new JsonbConfig().withPropertyNamingStrategy(
			  PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES
			).withNullValues(true)
			.withLocale(new Locale("tr","TR"))
			.withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL)
		    .withFormatting(true);
		Jsonb jsonb = JsonbBuilder.create(config);
		Employee jack= new Employee("1", "Jack Bauer", "jackb@example.com", "TR1", BigDecimal.valueOf(100_000));
		String jsonEmployee = jsonb.toJson(jack);
		System.out.println(jsonEmployee);
		List<Employee> employees = Arrays.asList(
				new Employee("1", "Jack Shephard", "jack.shephard@example.com", "TR1", BigDecimal.valueOf(100_000.1234)),
				new Employee("2", "Kate Austen", "kate.austen@example.com", "TR2", BigDecimal.valueOf(200_000.54321))
		);
		String jsonArrayEmployees = jsonb.toJson(employees);
		System.out.println(jsonArrayEmployees);
	}
}
