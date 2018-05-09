package ru.skorikov;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by AlexSkorikov on 04.05.18.
 */
public class ParsePage implements Callable<ConcurrentHashMap> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ParseSite.class);
    /**
     * Parsing Page URL.
     */
    private String urlPage;
    /**
     * Utils for parse..
     */
    private ParseSiteUtil util;
    /**
     * Calendar for parse.
     */
    private Calendar calendar;

    /**
     * New Parse Page.
     * @param urlPage URL page.
     * @param start Start date.
     */
    ParsePage(String urlPage, Calendar start) {
        this.urlPage = urlPage;
        this.util = new ParseSiteUtil();
        this.calendar = start;
    }

    @Override
    public ConcurrentHashMap call() {
        ConcurrentHashMap<String, Vacansy> vacansyFromPage = new ConcurrentHashMap<>();
        try {
            util.getAllUrlMessageAndDateFromPage(this.urlPage, vacansyFromPage, this.calendar);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return vacansyFromPage;
    }
}
