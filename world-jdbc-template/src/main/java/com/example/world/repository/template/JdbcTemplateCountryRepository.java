package com.example.world.repository.template;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.world.entity.City;
import com.example.world.entity.Country;
import com.example.world.exception.EntityNotFoundException;
import com.example.world.repository.CityRepository;
import com.example.world.repository.CountryRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Repository
public class JdbcTemplateCountryRepository implements CountryRepository {
	private static final String SELECT_COUNTRY_BY_CODE = "SELECT * FROM COUNTRY WHERE CODE=?";
	private static final String SELECT_COUNTRIES_BY_CONTINENT = "SELECT * FROM COUNTRY WHERE CONTINENT=?";
	private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM COUNTRY";
	private static final String INSERT_COUNTRY = "INSERT INTO COUNTRY(CODE,NAME,CONTINENT,POPULATION,SURFACEAREA) "
			+ "VALUES(?,?,?,?,?)";
	private static final String UPDATE_COUNTRY = "UPDATE COUNTRY SET POPULATION=?, SURFACEAREA=? " + "WHERE CODE=?";
	private static final String DELETE_COUNTRY = "DELETE FROM COUNTRY WHERE CODE=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CityRepository cityRepository;

	public Optional<Country> findOne(String code) {
		Country country = jdbcTemplate.queryForObject(SELECT_COUNTRY_BY_CODE, new Object[] { code },
				new BeanPropertyRowMapper<Country>(Country.class));
		Collection<City> cities = cityRepository.findByCountryCode(code);
		cities.forEach(city -> city.setCountry(country));
		country.setCities(cities);
		return Optional.ofNullable(country);
	}

	public Collection<Country> findAll() {
		List<Country> countries = jdbcTemplate.query(SELECT_ALL_COUNTRIES, new Object[] {},
				new BeanPropertyRowMapper<Country>(Country.class));
		countries.forEach(country -> country.setCities(cityRepository.findByCountryCode(country.getCode())));
		return countries;
	}

	public Country add(Country country) {
		int rowsAffected = jdbcTemplate.update(INSERT_COUNTRY, new Object[] { country.getCode(), country.getName(),
				country.getContinent(), country.getPopulation(), country.getSurfaceArea() });
		System.err.println(String.format("%d rows affected.", rowsAffected));
		return country;
	}

	public Country update(Country country) {
		String code = country.getCode();
		Optional<Country> existing = findOne(code);
		if (!existing.isPresent())
			throw new EntityNotFoundException("Country does not exist");
		jdbcTemplate.update(UPDATE_COUNTRY,
				new Object[] { country.getPopulation(), country.getSurfaceArea(), country.getCode() });
		return country;
	}

	public Optional<Country> remove(String code) {
		Optional<Country> country = findOne(code);
		if (country.isPresent()) {
			jdbcTemplate.update(DELETE_COUNTRY, new Object[] { code });
		}
		return country;
	}

	public Collection<Country> getByContinent(String continent) {
		return jdbcTemplate.query(SELECT_COUNTRIES_BY_CONTINENT, new Object[] { continent },
				new BeanPropertyRowMapper<Country>(Country.class));
	}

	@Override
	public Collection<String> getContinents() {
		return jdbcTemplate.queryForList("SELECT DISTINCT CONTINENT FROM COUNTRY", String.class);
	}

}
