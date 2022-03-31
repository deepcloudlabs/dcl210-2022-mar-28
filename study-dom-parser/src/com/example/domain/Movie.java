package com.example.domain;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Movie {

    private String title;
    private String year;
    private String imdb;
    private List<String> genres;

    public Movie() {
        genres = new LinkedList<String>();
    }

    public void setGenre(String genre) {
        genres.add(genre);
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
