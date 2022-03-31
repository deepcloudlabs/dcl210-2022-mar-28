package com.example.world.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Entity
@Table(name = "Country")
@DynamicUpdate
@DynamicInsert
@NamedEntityGraphs({
		@NamedEntityGraph(name = "graph.Country.cities", attributeNodes = @NamedAttributeNode(value = "cities", subgraph = "cities"), subgraphs = @NamedSubgraph(name = "cities", attributeNodes = @NamedAttributeNode("country"))),
		@NamedEntityGraph(name = "graph.Country.citylangs", attributeNodes = {
				@NamedAttributeNode(value = "cities", subgraph = "cities"),
				@NamedAttributeNode(value = "languages", subgraph = "languages") }, subgraphs = {
						@NamedSubgraph(name = "cities", attributeNodes = @NamedAttributeNode("country")),
						@NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country")) }),
		@NamedEntityGraph(name = "graph.Country.languages", attributeNodes = @NamedAttributeNode(value = "languages", subgraph = "languages"), subgraphs = @NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country"))) })

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
	@JsonIgnore
	private City capitalCity;
	@OneToMany(mappedBy = "country")
	@JsonIgnore
	private List<City> cities;
	@OneToMany
	@JoinTable()
	@JsonIgnore
	private Set<CountryLanguage> languages;

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

	public Set<CountryLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<CountryLanguage> languages) {
		this.languages = languages;
	}

	@Override
	public String toString() {
		return "Country [kod=" + kod + ", name=" + name + ", continent=" + continent + ", population=" + population
				+ ", surface=" + surface + ", capitalCity=" + capitalCity + "]";
	}

}
