package com.example.application;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorldApplication {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/world?useSSL=false";
	public static void main(String[] args) throws SQLException {
		try(var connection = DriverManager.getConnection(JDBC_URL, "root", "Secret_123");){
			System.err.println("Connected to the database.");
			var distinctContinents = 
					connection.prepareStatement("select distinct continent from country");
			var resultSet = distinctContinents.executeQuery();
			var continents = new ArrayList<String>();
			while (resultSet.next()) {
				var continent = resultSet.getString(1);
				continents.add(continent);
			}
			continents.sort(String::compareTo);
			System.out.println(continents);
		}
	}

}
