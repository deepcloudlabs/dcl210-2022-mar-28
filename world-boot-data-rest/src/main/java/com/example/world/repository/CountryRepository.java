package com.example.world.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.world.entity.Country;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
// Spring Data Jpa
public interface CountryRepository extends JpaRepository<Country, String>, JpaSpecificationExecutor<Country> {

	@EntityGraph(value = "graph.Country.cities", type = EntityGraphType.FETCH)
	Country findOneByKod(String code);
	@Query(nativeQuery = true, value = "select c from COUNTRY c where c.continent=:continent")
	//@Query("select c from Country c where c.continent=:continent")
    List<Country> bulGetir(String continent);
    
	Page<Country> findByContinent(Pageable page, String continent);
	Collection<Country> findTop10ByOrderByPopulationDesc();

	Page<Country> findAll(Pageable page);
}
