package com.example.use;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.example.domain.City;
import com.example.handler.GenericHandler;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class UseCityHandler {
	public static void main(String[] args) {

		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			GenericHandler<City> handler = new GenericHandler<City>(City.class);
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new FileInputStream(new File("resources","cities.xml"))));
			for (City city : handler.getElements(City.class)) {
				System.err.println("City Id: " + city.getID());
				System.err.println("City name: " + city.getName());
				System.err.println("City population: " + city.getPopulation());
				System.err.println("------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
