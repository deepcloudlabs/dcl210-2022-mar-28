package com.example.dom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class TestDOMParser {

    private Document document;
    private int[] histogram = new int[]{0, 0, 0, 0, 0, 0};

    public void doQuery(String xmlFileName, String outXmlFileName, String search) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(xmlFileName));
            Node movieList = document.getDocumentElement();
            if (movieList.getNodeType() == Node.ELEMENT_NODE) {
                NodeList movies = movieList.getChildNodes();
                for (int i = 0; i < movies.getLength(); i++) {
                    if (movies.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        NodeList movie = movies.item(i).getChildNodes();
                        for (int j = 0; j < movie.getLength(); j++) {
                            if (movie.item(j).getNodeName().compareTo("year") == 0) {
                                NodeList movieTitle = movie.item(j).getChildNodes();
                                for (int k = 0; k < movieTitle.getLength(); k++) {
                                    if (movieTitle.item(k).getNodeType() == Node.TEXT_NODE) {
                                        int year = Integer.parseInt(movieTitle.item(k).getNodeValue());
                                        if (year < 1960) {
                                            histogram[0]++;
                                        } else if (year < 1970) {
                                            histogram[1]++;
                                        } else if (year < 1980) {
                                            histogram[2]++;
                                        }else if (year < 1990) {
                                            histogram[3]++;
                                        }else if (year < 2000) {
                                            histogram[4]++;
                                        }else{
                                            histogram[5]++;
                                        }
                                        if (year < 1970 || year > 1979) {
                                            movieList.removeChild(movies.item(i));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(document);
            Result dest = new StreamResult(new File(outXmlFileName));
            aTransformer.transform(src, dest);
            String []range= new String[]{"<1960","1960-1969","1970-1979","1980-1989","1990-1999",">1999"};
            for (int i=0;i<histogram.length;++i){
                System.out.println("There are "+histogram[i]+" movies "+range[i]);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        TestDOMParser query = new TestDOMParser();
        query.doQuery("src/data/movies.xml", "src/data/movies_of_70s.xml", "The");
    }
}
