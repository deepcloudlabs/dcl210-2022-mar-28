package com.example.imdb.handlers;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class SimpleSaxMovieHandler extends DefaultHandler {

    private Map<String, HistogramBin> genreHistogram;
    private boolean isGenre = false;

    public SimpleSaxMovieHandler() {
        genreHistogram = new HashMap<String, HistogramBin>();
    }

    public Map<String, HistogramBin> getgenreHistogram() {
        return genreHistogram;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (Character.isWhitespace(ch[start])) {
            return;
        }
        String content = String.copyValueOf(ch, start, length);
        if (isGenre) {
            HistogramBin bin = genreHistogram.get(content);
            if (bin == null) {
                genreHistogram.put(content, new HistogramBin(1));
            } else {
                bin.increment();
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.err.println("End of Parsing!");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("genre".equals(qName.toLowerCase())) {
            isGenre = false;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        System.err.println("Parsing is started!");
        genreHistogram.clear();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("genre".equals(qName.toLowerCase())) {
            isGenre = true;
        }
    }
}
