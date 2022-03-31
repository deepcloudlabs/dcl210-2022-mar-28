package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class AddCountryApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryRepository countryRepository = container.getBean(CountryRepository.class);
		Country unknown = new Country("now", "unknown", "Asia", 1000L, 100_000.);
		countryRepository.add(unknown);
		container.close();
	}
}
