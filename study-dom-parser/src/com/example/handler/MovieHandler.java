package com.example.handler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.domain.Movie;
import com.example.domain.MovieComparator;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class MovieHandler extends DefaultHandler {

    private List<Movie> movies;
    private int beginYear;
    private int endYear;
    private String openingTag;
    private String value;
    private String imdb;
    private Movie movie;

    public List<Movie> getMovies() {
        return movies;
    }

    public int getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @Override
    public void endDocument() throws SAXException {
        Collections.sort(movies, new MovieComparator());
        System.err.println("Parsing is done.");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String tag = qName.trim().toLowerCase();
        if (tag.equals("title")) {
            movie.setTitle(value);
        } else if (tag.equals("year")) {
            movie.setYear(value);
        } else if (tag.equals("genre")) {
            movie.setGenre(value);
        } else if (tag.equals("movie")) {
            int year = Integer.parseInt(movie.getYear());
            if ((year >= beginYear) && (year <= endYear)) {
                movies.add(movie);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        StringBuilder buffer = new StringBuilder();
        for (int i = start, j = 0; j < length; ++j, ++i) {
            buffer.append(ch[i]);
        }
        value = buffer.toString();
        value = value.trim();
    }

    @Override
    public void startDocument() throws SAXException {
        if (movies == null) {
            movies = new LinkedList<Movie>();
        }
        movies.clear();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        openingTag = qName.trim().toLowerCase();
        if (openingTag.equals("movie")) {
            movie = new Movie();
        } else if (openingTag.equals("title")) {
            imdb = attributes.getValue("imdb");
            movie.setImdb(imdb);
        }
    }
}
