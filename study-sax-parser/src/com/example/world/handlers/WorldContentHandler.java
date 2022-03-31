package com.example.world.handlers;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
*
* @author Binnur Kurt (binnur.kurt@gmail.com)
*/
public class WorldContentHandler extends DefaultHandler{
	private Map<String,Integer> continentCountries= new HashMap<>();
	private boolean insideContinent;

	public Map<String, Integer> getContinentCountries() {
		return continentCountries;
	}

	@Override
	public void startDocument() throws SAXException {
		continentCountries.clear();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		insideContinent=false;
		if (qName.equals("Continent"))
			insideContinent=true;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		insideContinent=false;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (insideContinent){
			String continent= 
					String.copyValueOf(ch, start, length);
			continent= continent.trim();
			Integer counter= continentCountries.get(continent);
			if (counter==null)
				counter=0;
			counter= counter+1;
			continentCountries.put(continent, counter);
		}
	}
	
	
}
