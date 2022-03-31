package com.example.world;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;
import com.example.world.repository.MongoCountryRepository;
import com.mongodb.client.DistinctIterable;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@SpringBootApplication
public class WorldBootDataRestApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorldBootDataRestApplication.class);

	@Autowired
	private MongoCountryRepository mongoCountryRepository;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private CountryRepository countryRepository;

	public static void main(String[] args) {
		SpringApplication.run(WorldBootDataRestApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... arg0) throws Exception {
		countryRepository.findTop10ByOrderByPopulationDesc()
		.forEach(System.out::println);
		System.err.println("From mongodb:");
		mongoCountryRepository.findTop10ByOrderByPopulationDesc()
		.forEach(System.err::println);
		System.err.println("Done.");
		Specification<Country> asianHighPopulated = (root, query, criteriaBuilder) -> {
			return criteriaBuilder.and(criteriaBuilder.gt(root.get("population"), 100_000_000),
					criteriaBuilder.equal(root.get("continent"), "Asia"),
					criteriaBuilder.gt(root.get("surface"), 1_000_000.));
		};

		LOGGER.info("Number of asian countries is " + countryRepository.count(asianHighPopulated));
		countryRepository.findAll(asianHighPopulated).stream().map(Object::toString).forEach(LOGGER::info);
		Country turkey = countryRepository.findOneByKod("TUR");
		LOGGER.info("turkey.getCities().getClass(): " + turkey.getCities().getClass());
		LOGGER.info("turkey.getCapitalCity().getClass(): " + turkey.getCapitalCity().getClass());
		LOGGER.info("turkey.getCapitalCity().getClass(): " + turkey.getCapitalCity().getName());
		turkey.getCities().stream().map(Object::toString).forEach(LOGGER::info);

		turkey = countryRepository.findOneByKod("TUR");
		turkey.getCities().stream().map(Object::toString).forEach(LOGGER::info);
		long numberOfCountries = mongoCountryRepository.count();
		LOGGER.info("numberOfCountries: " + numberOfCountries);
		mongoCountryRepository.findTop10ByOrderByPopulationDesc().stream().map(Object::toString).forEach(LOGGER::info);
		// db.countries1.distinct("continent")
		DistinctIterable<String> continents = mongoTemplate.getCollection("countries1").distinct("continent",
				String.class);
		continents.forEach((String s) -> System.out.println(s));
	}
}
