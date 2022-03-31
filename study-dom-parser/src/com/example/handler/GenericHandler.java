package com.example.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class GenericHandler<T> extends DefaultHandler {

    private List<T> elements;
    private T activeElement;
    private Field field;
    private Map<String, Field> fieldMap;
    private Class<?> elementClass;
    private String tag;

    public GenericHandler(Class<T> elementClass) {
        this.elementClass = elementClass;
        tag = elementClass.getSimpleName();
        tag = Character.toLowerCase(tag.charAt(0)) + tag.substring(1);
        fieldMap = new HashMap<String, Field>();
        for (Field field : elementClass.getDeclaredFields()) {
            fieldMap.put(field.getName(), field);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.err.println("Ayrıştırma işlemi sona erdi...");
    }

    @Override
    public void startDocument() throws SAXException {
        System.err.println("Ayrıştırma işlemi başladı...");
        elements = new ArrayList<T>();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = String.copyValueOf(ch,start,length).trim();
        if (s.isEmpty()) return;
        if (field != null && activeElement != null) {
            try {
                if (field.getGenericType() == int.class) {
                    int val = Integer.parseInt(s);
                    field.setAccessible(true);
                    field.setInt(activeElement, val);
                } else if (field.getGenericType() == double.class) {
                    double val = Double.parseDouble(s);
                    field.setAccessible(true);
                    field.setDouble(activeElement, val);
                } else if (field.getGenericType() == String.class) {
                    field.setAccessible(true);
                    field.set(activeElement, s);
                }
            } catch (Exception ex) {
            	System.err.println(ex);
            }
        }

    }

    @SuppressWarnings("unchecked")
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(tag)) {
            try {
                activeElement = (T) elementClass.getDeclaredConstructor().newInstance();
                for (int i = 0; i < attributes.getLength(); ++i) {
                    Field f = fieldMap.get(attributes.getQName(i));
                    if (f != null && activeElement != null) {
                        try {
                            if (field.getGenericType() == int.class) {
                                int val = Integer.parseInt(attributes.getValue(i));
                                field.setAccessible(true);
                                field.setInt(activeElement, val);
                            } else if (field.getGenericType() == double.class) {
                                double val = Double.parseDouble(attributes.getValue(i));
                                field.setAccessible(true);
                                field.setDouble(activeElement, val);
                            } else if (field.getGenericType() == String.class) {
                                field.setAccessible(true);
                                field.set(activeElement, attributes.getValue(i));
                            }
                        } catch (Exception ex) {
                        }
                    }
                }
            } catch (Exception ex) {
            }
        } else {
            for (int i = 0; i < attributes.getLength(); ++i) {
                Field f = fieldMap.get(attributes.getQName(i));
                if (f != null && activeElement != null) {
                    try {
                        if (field.getGenericType() == int.class) {
                            int val = Integer.parseInt(attributes.getValue(i));
                            field.setAccessible(true);
                            field.setInt(activeElement, val);
                        } else if (field.getGenericType() == double.class) {
                            double val = Double.parseDouble(attributes.getValue(i));
                            field.setAccessible(true);
                            field.setDouble(activeElement, val);
                        } else if (field.getGenericType() == String.class) {
                            field.setAccessible(true);
                            field.set(activeElement, attributes.getValue(i));
                        }
                    } catch (Exception ex) {
                    	System.err.println(ex.getMessage());
                    }
                }
            }
            field = fieldMap.get(qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(tag)) {
            elements.add(activeElement);
        }
    }

    public List<T> getElements(Class<?> cls) {
        return (List<T>) elements;
    }
}
