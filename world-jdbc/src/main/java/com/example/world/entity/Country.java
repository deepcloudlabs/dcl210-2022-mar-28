package com.example.world.entity;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Country {
	private String code;
	private String name;
	private String continent;
	private Long population;
	private Double surfaceArea;

	public Country() {
	}

	public Country(String code, String name, String continent, Long population, Double surfaceArea) {
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.population = population;
		this.surfaceArea = surfaceArea;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Double getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(Double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + ", continent=" + continent + ", population=" + population
				+ ", surfaceArea=" + surfaceArea + "]";
	}

}
