package com.example.world.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Entity
@Table(name = "Country")
@DynamicUpdate
@DynamicInsert
@NamedQueries({ @NamedQuery(name = "Country.findAll", query = "select c from Country c"),
		@NamedQuery(name = "Country.findByContinent", query = "select c from Country c "
				+ "where c.continent=:continent"),
		@NamedQuery(name = "Country.findContinents", query = "select distinct(c.continent) from Country c ") })
public class Country {
	@Id
	@Column(name = "code")
	private String kod;
	@Column(name = "name")
	private String name;
	@Column(name = "continent")
	private String continent;
	@Column(name = "population")
	private Long population;
	@Column(name = "surfacearea")
	private Double surface;
	@OneToOne
	@JoinColumn(name = "capital")
	private City capitalCity;
	@OneToMany(mappedBy = "country")
	private List<City> cities;

	public Country() {
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public Double getSurface() {
		return surface;
	}

	public void setSurface(Double surface) {
		this.surface = surface;
	}

	public City getCapitalCity() {
		return capitalCity;
	}

	public void setCapitalCity(City capitalCity) {
		this.capitalCity = capitalCity;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "Country [kod=" + kod + ", name=" + name + ", continent=" + continent + ", population=" + population
				+ ", surface=" + surface + ", capitalCity=" + capitalCity + "]";
	}

}
