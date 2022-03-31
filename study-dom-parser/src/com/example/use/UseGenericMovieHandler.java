package com.example.use;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import com.example.domain.Kitap;
import com.example.handler.GenericHandler;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class UseGenericMovieHandler {

	public static void main(String[] args) {

        try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			GenericHandler<Kitap> handler = new GenericHandler<Kitap>(Kitap.class);
            reader.setContentHandler(handler);
            reader.parse("resources/books.xml");
            for (Kitap kitap: handler.getElements(Kitap.class)) {
                System.err.println("Başlık: " + kitap.getBaşlık());
                System.err.println("------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
