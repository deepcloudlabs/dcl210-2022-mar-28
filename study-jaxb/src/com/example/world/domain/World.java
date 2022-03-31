package com.example.world.domain;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
@XmlRootElement(name = "world")
public class World {
	private Collection<Country> countries;

	public World() {
	}

	@XmlElementWrapper
	@XmlElementRefs({ @XmlElementRef(type = Country.class) })
	public Collection<Country> getCountries() {
		return countries;
	}

	public void setCountries(Collection<Country> countries) {
		this.countries = countries;
	}

}
