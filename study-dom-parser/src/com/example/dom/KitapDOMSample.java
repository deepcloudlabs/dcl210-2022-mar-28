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
public class KitapDOMSample {

    private Document document;

    public void doQuery(String xmlFileName, String outXmlFileName) {
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
                        int sayfa=0;
                        double fiyat=0;
                        for (int j = 0; j < movie.getLength(); j++) {
                            if (movie.item(j).getNodeName().compareTo("sayfa") == 0) {
                                NodeList movieTitle = movie.item(j).getChildNodes();
                                for (int k = 0; k < movieTitle.getLength(); k++) {
                                    if (movieTitle.item(k).getNodeType() == Node.TEXT_NODE) {
                                        sayfa = Integer.parseInt(movieTitle.item(k).getNodeValue());
                                    }
                                }
                            }
                            if (movie.item(j).getNodeName().compareTo("fiyat") == 0) {
                                NodeList movieTitle = movie.item(j).getChildNodes();
                                for (int k = 0; k < movieTitle.getLength(); k++) {
                                    if (movieTitle.item(k).getNodeType() == Node.TEXT_NODE) {
                                        fiyat = Double.parseDouble(movieTitle.item(k).getNodeValue());
                                    }
                                }
                            }
                        }
                        System.err.println("Sayfa: "+sayfa);
                        System.err.println("Fiyat: "+fiyat);
                        if (fiyat >= 20 || sayfa >= 200) {
                            movieList.removeChild(movies.item(i));
                        }

                    }
                }
            }
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(document);
            Result dest = new StreamResult(new File(outXmlFileName));
            aTransformer.transform(src, dest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        KitapDOMSample query = new KitapDOMSample();
        query.doQuery("resources/books.xml", "resources/ince_kitap.xml");
    }
}
