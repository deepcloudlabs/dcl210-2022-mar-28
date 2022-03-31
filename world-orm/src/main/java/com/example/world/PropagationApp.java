package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.service.CountryService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class PropagationApp {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryService countryService = container.getBean(CountryService.class);
		countryService.changePopulation("TUR", 100);
		container.close();
	}
}
