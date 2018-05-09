package ru.skorikov;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by AlexSkorikov on 04.05.18.
 */
public class ParseSiteUtil {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ParseSite.class);

    /**
     * Get all pages from forum.
     * @param url url.
     * @return pages.
     */
    public Integer getAllPages(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        assert document != null;
        Element element = document.getElementsByAttributeValue("style", "text-align:left").get(0);
        Elements elements = element.children();
        Element last = elements.last();
        return Integer.parseInt(last.text());
    }

    /**
     * Get All url, message and data from page.
     * @param urlPage url paage.
     * @param map map for vacancy
     * @param endData end date.
     * @throws ParseException exception.
     */
    public void getAllUrlMessageAndDateFromPage(String urlPage, ConcurrentHashMap<String, Vacansy> map, Calendar endData) throws ParseException {
        Document document = null;
        try {
            document = Jsoup.connect(String.valueOf(urlPage)).userAgent("Mozilla/5.0").timeout(0).get();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        assert document != null;
        Element tabl = document.select("tbody").get(2);
        Elements elements = tabl.select("tr");

        for (int i = 1; i < elements.size(); i++) {
            Element element = elements.get(i);
            Elements data = element.select("td");
            String urlVacancy = data.get(1).child(0).attr("href");
            String message = data.get(1).child(0).text();
            String datePost = data.get(5).text();

            if ((message.toLowerCase().contains("java".toLowerCase()))
                    && (!message.toLowerCase().contains("script"))) {
                if (validatyDatePost(datePost, endData) && validatyFirstPost(urlVacancy)) {
                    Calendar dateVacan = getDatePost(datePost);
                    map.putIfAbsent(urlVacancy, new Vacansy(message, dateVacan));
                }
            }
        }
    }

    /**
     * Get page for Parse.
     *
     * @param url paga URL
     * @param num num Page
     * @return Parse URL
     */
    public String getPageForParse(String url, int num) {
        StringBuilder builder = new StringBuilder();
        builder.append(url);
        builder.append("/");
        builder.append(num);
        return String.valueOf(builder);
    }

    /**
     * Validaty first post.
     *
     * @param url url post.
     * @return is validaty
     *                        <p>
     *                        Проверка на некропост.
     */
    private boolean validatyFirstPost(String url) {
        boolean isValid = false;
        Calendar today = Calendar.getInstance();
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        assert document != null;
        Elements elements = document.getElementsByAttributeValue("class", "msgFooter");
        Element ferst = elements.first();
        String vacan = ferst.text();
        Calendar dateFirstPost = null;
        try {
            dateFirstPost = getDatePost(vacan);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        assert dateFirstPost != null;
        if (today.get(Calendar.YEAR) == dateFirstPost.get(Calendar.YEAR)) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Validaty date post.
     *
     * @param datePost date Post Vacansy.
     * @param endDate  end Date search
     * @return is valid
     *                        <p>
     *                        Проверка того, что пост в диапазоне от текущей даты до даты предыдущей загрузки.
     */
    private boolean validatyDatePost(String datePost, Calendar endDate) {
        boolean isValid = false;
        try {
            if (endDate.getTimeInMillis() < getDatePost(datePost).getTimeInMillis()) {
                isValid = true;
            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isValid;
    }

    /**
     * Get Date post from string.
     *
     * @param string string
     * @return calendar
     * @throws ParseException exception
     */
    private Calendar getDatePost(String string) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        if (string.contains("сегодня")) {
            int hour = Integer.parseInt(string.substring(9, 11));
            int minutes = Integer.parseInt(string.substring(12, 14));
            calendar.set(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DATE),
                    hour, minutes);
        } else {
            if (string.contains("вчера")) {
                int hour = Integer.parseInt(string.substring(7, 9));
                int minutes = Integer.parseInt(string.substring(10, 12));
                calendar.set(calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE) - 1,
                        hour, minutes);
            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, HH:mm");
                calendar.setTime(format.parse(string));
            }
        }
        return calendar;
    }
}
