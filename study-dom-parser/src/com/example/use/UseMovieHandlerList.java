package com.example.use;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import com.example.domain.Movie;
import com.example.handler.MovieHandler;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class UseMovieHandlerList {
	public static void main(String[] args) {

		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			MovieHandler handler = new MovieHandler();
			handler.setBeginYear(1970);
			handler.setEndYear(1979);
			reader.setContentHandler(handler);
			reader.parse("resources/movies.xml");
			for (Movie movie : handler.getMovies()) {
				System.err.println("Movie Title: " + movie.getTitle());
				System.err.println("Movie Genres: " + movie.getGenres());
				System.err.println("Movie Year: " + movie.getYear());
				System.err.println("IMDB: " + movie.getImdb());
				System.err.println("------------------------------------------");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
