package ru.skorikov;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @ author: Alex_Skorikov.
 * @ date: 23.04.18
 * @ version: Java_Kurs_JDBC
 */
public class MyHandler extends DefaultHandler {
    /**
     * Rezalt.
     */
    private Long rezalt = 0L;
    /**
     * Intermediate rezalt.
     */
    private Long temp = 0L;

    /**
     * Get rezalt.
     * @return rezalt
     */
    public Long getRezalt() {
        return rezalt;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("element")) {
            temp = Long.parseLong(attributes.getValue("Data"));
        }
        rezalt += temp;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }
}
