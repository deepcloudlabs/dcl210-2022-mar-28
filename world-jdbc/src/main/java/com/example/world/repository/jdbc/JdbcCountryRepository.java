package com.example.world.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.world.entity.Country;
import com.example.world.exception.EntityNotFoundException;
import com.example.world.repository.CountryRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Repository
public class JdbcCountryRepository implements CountryRepository {
	// COLUMN NAMES
	private static final String COLUMN_SURFACE_AREA = "surfaceArea";
	private static final String COLUMN_POPULATION = "population";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_CONTINENT = "continent";
	private static final String COLUMN_CODE = "code";

	// SQL STATEMENTS
	private static final String SELECT_COUNTRY_BY_CODE = "select * from country where code=?";
	private static final String SELECT_COUNTRIES_BY_CONTINENT = "select * from country where continent=?";
	private static final String SELECT_CONTINENTS = "select distinct continent from country";
	private static final String SELECT_COUNTRIES = "select * from country";
	private static final String DELETE_COUNTRY_BY_CODE = "delete from country where code=?";
	private static final String ADD_COUNTRY = "insert into country(code,name,continent,population,surfaceArea) values(?,?,?,?,?)";
	private static final String UPDATE_COUNTRY = "update country set population=? , surfaceArea=? where code=?";
	@Autowired
	private DataSource dataSource;

	public Optional<Country> findOne(String code) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement st = conn.prepareStatement(SELECT_COUNTRY_BY_CODE);
			st.setString(1, code);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Country country = new Country();
				country.setCode(code);
				country.setName(rs.getString(COLUMN_NAME));
				country.setContinent(rs.getString(COLUMN_CONTINENT));
				country.setPopulation(rs.getLong(COLUMN_POPULATION));
				country.setSurfaceArea(rs.getDouble(COLUMN_SURFACE_AREA));
				return Optional.of(country);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Collection<Country> findAll() {
		List<Country> countries = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SELECT_COUNTRIES);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				countries.add(extractCountryFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countries;
	}

	public Country add(Country country) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement st = conn.prepareStatement(ADD_COUNTRY);
			st.setString(1, country.getCode());
			st.setString(2, country.getName());
			st.setString(3, country.getContinent());
			st.setLong(4, country.getPopulation());
			st.setDouble(5, country.getSurfaceArea());
			int rowsAffected = st.executeUpdate();
			System.err.println(String.format("%d rows affected.", rowsAffected));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country;
	}

	public Country update(Country country) {
		String code = country.getCode();
		Optional<Country> existing = findOne(code);
		if (!existing.isPresent())
			throw new EntityNotFoundException("Country does not exist");
		PreparedStatement statement;
		try (Connection connection = dataSource.getConnection()) {
			statement = connection.prepareStatement(UPDATE_COUNTRY);
			statement.setLong(1, country.getPopulation());
			statement.setDouble(2, country.getSurfaceArea());
			statement.setString(3, country.getCode());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country;
	}

	public Optional<Country> remove(String code) {
		Optional<Country> country = findOne(code);
		country.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(DELETE_COUNTRY_BY_CODE);
			statement.setString(1, code);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country;
	}

	public Collection<Country> getByContinent(String continent) {
		List<Country> countries = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SELECT_COUNTRIES_BY_CONTINENT);
			statement.setString(1, continent);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				countries.add(extractCountryFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countries;
	}

	private Country extractCountryFromResultSet(ResultSet rs) throws SQLException {
		Country country = new Country();
		country.setCode(rs.getString(COLUMN_CODE));
		country.setContinent(rs.getString(COLUMN_CONTINENT));
		country.setName(rs.getString(COLUMN_NAME));
		country.setPopulation(rs.getLong(COLUMN_POPULATION));
		country.setSurfaceArea(rs.getDouble(COLUMN_SURFACE_AREA));
		return country;
	}

	@Override
	public Set<String> getContinents() {
		Set<String> continents = new HashSet<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SELECT_CONTINENTS);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				continents.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return continents;
	}

}
