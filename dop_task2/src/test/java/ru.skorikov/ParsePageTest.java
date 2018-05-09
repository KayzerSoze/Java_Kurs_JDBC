package ru.skorikov;

import org.junit.Test;
import java.util.Calendar;

/**
 * Created by AlexSkorikov on 08.05.18.
 */
public class ParsePageTest {
    /**
     * new ParsePage.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tryParseBadPage() {
        String url = "url";
        Calendar calendar = Calendar.getInstance();
        ParsePage parsePage = new ParsePage(url, calendar);
        parsePage.call();
    }
}