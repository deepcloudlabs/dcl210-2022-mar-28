package com.example.application;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class WorldCountriesApplication {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/world?useSSL=false";
	public static void main(String[] args) throws SQLException {
		try(var connection = DriverManager.getConnection(JDBC_URL, "root", "Secret_123");){
			System.err.println("Connected to the database.");
			var continentCountries = 
					connection.prepareStatement("""
							select code,name,continent,population 
							from country
							where continent = ?
							""");
			continentCountries.setString(1, "Asia");
			var resultSet = continentCountries.executeQuery();
			var asianCountries = new ArrayList<Country>();
			while (resultSet.next()) {
				asianCountries.add(
					new Country(
							resultSet.getString("code"),
							resultSet.getString("name"),
							resultSet.getString("continent"),
							resultSet.getInt("population")
				  )
				);
			}
			asianCountries.sort(Comparator.comparing(Country::population).reversed());
			asianCountries.forEach(System.out::println);
		}
	}

}

record Country(String code,String name,String continent,int population) {}