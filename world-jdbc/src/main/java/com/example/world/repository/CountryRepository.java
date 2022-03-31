package com.example.world.repository;

import java.util.Collection;
import java.util.Set;

import com.example.world.entity.Country;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CountryRepository extends GenericRepository<Country, String> {
	Collection<Country> getByContinent(String continent);

	Set<String> getContinents();
}
