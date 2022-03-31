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
public class UseKitapHandler {
	public static void main(String[] args) {

		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			GenericHandler<Kitap> handler = new GenericHandler<Kitap>(Kitap.class);
			reader.setContentHandler(handler);
			reader.parse("resources/books.xml");
			for (Kitap kitap : handler.getElements(Kitap.class)) {
				System.err.println("Kitap Adı: " + kitap.getBaşlık());
				System.err.println("Yazar    : " + kitap.getYazar());
				System.err.println("Yayın Evi: " + kitap.getYayın_evi());
				System.err.println("Yıl      : " + kitap.getYıl());
				System.err.println("Sayfa    : " + kitap.getSayfa());
				System.err.println("Fiyat    : " + kitap.getFiyat());
				System.err.println("Kod      : " + kitap.getKod());
				System.err.println("------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
