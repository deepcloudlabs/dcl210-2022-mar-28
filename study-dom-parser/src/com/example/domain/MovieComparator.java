package com.example.domain;

import java.util.Comparator;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class MovieComparator implements Comparator<Movie> {

    public int compare(Movie o1, Movie o2) {
        int yearLeft= Integer.parseInt(o1.getYear());
        int yearRight= Integer.parseInt(o2.getYear());
        return yearLeft-yearRight;
    }

}
