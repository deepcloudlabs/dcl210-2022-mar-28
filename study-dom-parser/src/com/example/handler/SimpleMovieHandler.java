package com.example.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class SimpleMovieHandler extends DefaultHandler {

    private int counter = 0;
    private boolean isMovieTag = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (Character.isWhitespace(ch[start])) {
            return;
        }
        if (isMovieTag) {
            System.err.println("Content: " + String.copyValueOf(ch, start, length));
        }
    }

//    @Override
//    public void endDocument() throws SAXException {
//        System.err.println("Parsing ended!");
//    }
//
//    @Override
//    public void startDocument() throws SAXException {
//        System.err.println("Parsing started!");
//    }
//
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("Movie".equalsIgnoreCase(localName)) {
            counter++;
            isMovieTag = true;
        }
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        isMovieTag = false;
    }
}
