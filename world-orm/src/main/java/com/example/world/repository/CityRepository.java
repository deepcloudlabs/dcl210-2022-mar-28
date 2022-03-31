package com.example.world.repository;

import java.util.Collection;

import com.example.world.entity.City;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CityRepository extends GenericRepository<City, Long> {
	Collection<City> findByCountryCode(String countryCode);
}
