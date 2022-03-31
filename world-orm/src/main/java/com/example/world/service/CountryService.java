package com.example.world.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public void changePopulation(String code, long change) {
		Optional<Country> country = countryRepository.findOne(code);
		country.ifPresent(c -> {
			c.setPopulation(c.getPopulation() + change);
			countryRepository.update(c);
		});
	}
}
