package ru.skorikov;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.core.Is.is;


/**
 * Created by AlexSkorikov on 09.05.18.
 */
public class ParseSiteUtilTest {
    /**
     * Get all pages.
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAllPages() {
        ParseSiteUtil util = new ParseSiteUtil();
        util.getAllPages("url");
    }

    /**
     * Get All URL and Pages.
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAllUrlMessageAndDateFromPage() {
        ParseSiteUtil util = new ParseSiteUtil();
        try {
            util.getAllUrlMessageAndDateFromPage("url", new ConcurrentHashMap<String, Vacansy>(), Calendar.getInstance());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get page for parse.
     */
    @Test
    public void getPageForParse() {
        ParseSiteUtil util = new ParseSiteUtil();
        Assert.assertThat(util.getPageForParse("url", 1), is("url/1"));
    }
}