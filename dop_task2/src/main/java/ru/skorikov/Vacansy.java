package ru.skorikov;

import java.util.Calendar;

/**
 * Created by AlexSkorikov on 02.05.18.
 */
public class Vacansy {
    /**
     * URL vacancy.
     */
    private String url;
    /**
     * Vacancy message.
     */
    private String title;
    /**
     * Date vacansy.
     */
    private Calendar dateVacancy;

    /**
     * new Vacancy.
     *
     * @param title       title.
     * @param dateVacancy date.
     */
    public Vacansy(String title, Calendar dateVacancy) {
        this.title = title;
        this.dateVacancy = dateVacancy;
    }

    /**
     * new Vacancy.
     *
     * @param url         urlVacancy.
     * @param title       title.
     * @param dateVacancy date.
     */
    public Vacansy(String url, String title, Calendar dateVacancy) {
        this.url = url;
        this.title = title;
        this.dateVacancy = dateVacancy;
    }

    /**
     * get Url vacancy.
     *
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * get title vacancy.
     *
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * get date vacancy.
     *
     * @return date.
     */
    public Calendar getDateVacancy() {
        return dateVacancy;
    }
}
