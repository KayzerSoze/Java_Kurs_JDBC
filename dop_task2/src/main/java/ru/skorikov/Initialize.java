package ru.skorikov;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Created by AlexSkorikov on 03.05.18.
 */
public class Initialize {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ParseSite.class);
    /**
     * Url DB.
     */
    private String dataBaseURL;
    /**
     * User DB.
     */
    private String userDataBase;
    /**
     * User DB password.
     */
    private String userPassword;
    /**
     * Site parsing.
     */
    private String siteParsing;
    /**
     * Start date.
     */
    private String startDate;
    /**
     * Next parse date.
     */
    private String nextDate;
    /**
     * Frequency start.
     */
    private Integer frequency;
    /**
     * File config.
     */
    private File fileConfig;

    /**
     * Get url DB.
     *
     * @return url
     */
    public String getDataBaseURL() {
        return dataBaseURL;
    }

    /**
     * Get User DB.
     *
     * @return user.
     */
    public String getUserDataBase() {
        return userDataBase;
    }

    /**
     * Get user password.
     *
     * @return password.
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Get parsing site.
     *
     * @return site.
     */
    public String getSiteParsing() {
        return siteParsing;
    }

    /**
     * Get start date.
     *
     * @return date.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Get next date.
     *
     * @return date.
     */
    public String getNextDate() {
        return nextDate;
    }

    /**
     * Initialize.
     * @param file config
     */
    Initialize(File file) {
        this.fileConfig = file;
    }

    /**
     * Get parametres.
     * @param file config
     */
    public void getParam(File file) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("config");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    dataBaseURL = element.getElementsByTagName("urlDB").item(0).getTextContent();
                    userDataBase = element.getElementsByTagName("userID").item(0).getTextContent();
                    userPassword = element.getElementsByTagName("password").item(0).getTextContent();
                    siteParsing = element.getElementsByTagName("siteUrl").item(0).getTextContent();
                    startDate = element.getElementsByTagName("previousTime").item(0).getTextContent();
                    nextDate = element.getElementsByTagName("nextTime").item(0).getTextContent();
                    frequency = Integer.parseInt(element.getElementsByTagName("frequency").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    //дата предыдущей загрузки и
    //дата следующего запуска

    /**
     * Set date for next paarse.
     */
    public void setDateForNextParse() {
        File xmlFile = new File("src/main/resources/config.xml");
        DocumentBuilder db;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            doc.normalize();

            Node previDate = doc.getElementsByTagName("previousTime").item(0);
            Node nexDate = doc.getElementsByTagName("nextTime").item(0);
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, HH:mm");
            Calendar calendarPrev = Calendar.getInstance();
            Calendar calendarNext = Calendar.getInstance();
            calendarNext.set(calendarNext.get(Calendar.YEAR),
                    calendarNext.get(Calendar.MONTH),
                    calendarNext.get(Calendar.DATE) + frequency);
            //дата последней загрузки - текущая дата
            previDate.getFirstChild().setTextContent((String.valueOf(format.format(calendarPrev.getTime()))));
            //дата следующей загрузки
            nexDate.getFirstChild().setTextContent(String.valueOf(format.format(calendarNext.getTime())));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/config.xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
