package com.example.world.ui;

import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.example.world.handlers.WorldContentHandler;

public class StudySaxParser {

	public static void main(String[] args) throws Exception {
		SAXParserFactory factory= SAXParserFactory.newInstance();
		SAXParser parser= factory.newSAXParser();
		WorldContentHandler handler = new WorldContentHandler();
		parser.parse("resources/countries.xml", handler);
		Consumer<? super Entry<String, Integer>> consumer = 
				(entry)->  System.out.println(
				   String.format(
					  "%s: %s",entry.getKey(),entry.getValue()
				   )
				);
		handler.getContinentCountries()
		       .entrySet()
		       .forEach(
		    		 consumer
		          );
	}

}
