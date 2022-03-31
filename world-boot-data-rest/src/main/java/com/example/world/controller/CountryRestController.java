package com.example.world.controller;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestController
@RequestScope
@RequestMapping("countries")
public class CountryRestController {
	@Autowired
	private CountryRepository countryRepository;

	// http://localhost:9001/world/api/countries/TUR
	@GetMapping("{code}")
	public Country getByCode(@PathVariable String code) {
		return countryRepository.findOneByKod(code);
	}

	// http://localhost:9001/world/api/countries
	// ?continent=Asia&size=2&no=11
	// http://localhost:9001/world/api/countries?continent=Asia
	@GetMapping
	public Collection<Country> getAllByContinent(@RequestParam(required = false) String continent,
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "1") int no) {
		Pageable page = PageRequest.of(no, size);
		if (Objects.isNull(continent))
			return countryRepository.findAll(page).getContent();
		return countryRepository.findByContinent(page, continent).getContent();
	}

	@PostMapping
	public Country addCountry(@RequestBody Country country) {
		countryRepository.save(country);
		return country;
	}
}
