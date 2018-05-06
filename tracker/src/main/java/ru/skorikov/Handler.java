package ru.skorikov;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public class Handler extends DefaultHandler {
    /**
     * DataBase URL.
     */
    private String urlDataBase;
    /**
     * DataBase User.
     */
    private String user;
    /**
     * User password.
     */
    private String password;

    /**
     * Get DataBase URL.
     * @return URL
     */
    public String getUrlDataBase() {
        return urlDataBase;
    }

    /**
     * Get User.
     * @return user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Get User password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        urlDataBase = "";
        user = "";
        password = "";
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("url")) {
            urlDataBase = attributes.getValue("urlDB");
        }
        if (qName.equals("userID")) {
            user = attributes.getValue("user");
            password = attributes.getValue("password");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }
}
