package com.example.world.repository.orm;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.City;
import com.example.world.repository.CityRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Repository
public class CityJpaRepository implements CityRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public City add(City country) {
		entityManager.persist(country);
		return country;
	}

	@Override
	public City update(City country) {
		return entityManager.merge(country);
	}

	@Override
	@Transactional
	public Optional<City> remove(Long id) {
		City city = entityManager.find(City.class, id);
		if (Objects.nonNull(city)) {
			entityManager.remove(city);
			return Optional.of(city);
		}
		return Optional.empty();
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Optional<City> findOne(Long id) {
		City city = entityManager.find(City.class, id);
		if (Objects.nonNull(city))
			return Optional.of(city);
		return Optional.empty();
	}

	@Override
	public Collection<City> findAll() {
		List<City> cities = entityManager.createNamedQuery("fromCity.all", City.class).getResultList();
		return cities;
	}

	@Override
	public Collection<City> findByCountryCode(String code) {
		List<City> cities = entityManager.createNamedQuery("fromCity.byCountry", City.class).setParameter("code", code)
				.getResultList();
		return cities;
	}

}
