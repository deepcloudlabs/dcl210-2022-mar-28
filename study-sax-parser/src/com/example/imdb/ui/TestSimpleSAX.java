package com.example.imdb.ui;

import java.io.File;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.example.imdb.handlers.HistogramBin;
import com.example.imdb.handlers.SimpleSaxMovieHandler;

/**
*
* @author Binnur Kurt (binnur.kurt@gmail.com)
*/
public class TestSimpleSAX {

    public static void main(String[] args) {
        SAXParser parser = null;
        SAXParserFactory factory = 
        		SAXParserFactory.newInstance();
        try {
            parser = factory.newSAXParser();
            SimpleSaxMovieHandler handler = new SimpleSaxMovieHandler();
            parser.parse(new File("resources", "movies.xml"), handler);
            for (Entry<String,HistogramBin> genre : handler.getgenreHistogram().entrySet()) {
                System.err.println(genre.getKey()+": "+genre.getValue().getCount());
            }
        } catch (Exception ex) {
            Logger.getLogger(TestSimpleSAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
