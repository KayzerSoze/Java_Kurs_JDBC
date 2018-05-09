package ru.skorikov;

import org.junit.Test;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by AlexSkorikov on 08.05.18.
 */
public class ParseSiteTest {
    /**
     * new class ParseSite.
     */
    @Test(expected = IllegalArgumentException.class)
    public void parse() {
        ConcurrentHashMap<String, Vacansy> map = new ConcurrentHashMap<>();
        Calendar calendar = Calendar.getInstance();
        String url = "";
        ParseSite parseSite = new ParseSite(map, calendar, url);
        parseSite.parse();
    }
}