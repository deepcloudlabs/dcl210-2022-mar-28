package com.example.world.repository.orm;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.world.entity.CountryLanguage;
import com.example.world.entity.CountryLanguagePK;
import com.example.world.repository.CountryLanguageRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Repository
public class CountryLanguageJpaRepository implements CountryLanguageRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CountryLanguage add(CountryLanguage countryLanguage) {
		entityManager.persist(countryLanguage);
		return countryLanguage;
	}

	@Override
	public CountryLanguage update(CountryLanguage countryLanguage) {
		return null;
	}

	@Override
	public Optional<CountryLanguage> remove(CountryLanguagePK key) {
		CountryLanguage countryLanguage = entityManager.find(CountryLanguage.class, key);
		if (Objects.nonNull(countryLanguage)) {
			entityManager.remove(countryLanguage);
			Optional.of(countryLanguage);
		}
		return Optional.empty();
	}

	@Override
	public Optional<CountryLanguage> findOne(CountryLanguagePK key) {
		CountryLanguage countryLanguage = entityManager.find(CountryLanguage.class, key);
		if (Objects.nonNull(countryLanguage))
			return Optional.of(countryLanguage);
		return Optional.empty();
	}

	@Override
	public Collection<CountryLanguage> findAll() {
		return entityManager.createQuery("select cl from CountryLanguage cl", CountryLanguage.class).getResultList();
	}

}
