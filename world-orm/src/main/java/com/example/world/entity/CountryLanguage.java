package com.example.world.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.world.entity.converter.BooleanCharacterConverter;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Entity
public class CountryLanguage {
	@EmbeddedId
	private CountryLanguagePK countryLanguagePK;
	@Column(name = "isOfficial")
	@Convert(converter = BooleanCharacterConverter.class)
	private boolean official;
	private double percentage;

	@OneToOne()
	@JoinColumn(name = "countrycode", insertable = false, updatable = false, nullable = false)
	private Country country;

	public CountryLanguage() {
	}

	public CountryLanguagePK getCountryLanguagePK() {
		return countryLanguagePK;
	}

	public void setCountryLanguagePK(CountryLanguagePK countryLanguagePK) {
		this.countryLanguagePK = countryLanguagePK;
	}

	public boolean isOfficial() {
		return official;
	}

	public void setOfficial(boolean official) {
		this.official = official;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CountryLanguage [countryLanguagePK=" + countryLanguagePK + ", official=" + official + ", percentage="
				+ percentage + "]";
	}

}
