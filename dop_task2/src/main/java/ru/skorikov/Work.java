package ru.skorikov;

import org.apache.log4j.Logger;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by AlexSkorikov on 06.05.18.
 */
public class Work {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Work.class);
    /**
     * File config.
     */
    private File config;
    /**
     * Initialisator.
     */
    private Initialize initialize;
    /**
     * Vacancy Map.
     */
    private ConcurrentHashMap<String, Vacansy> vacansy;
    /**
     * Time start programm.
     */
    private Calendar today;

    /**
     * New Work.
     * @param file file config.
     */
    public Work(File file) {
        this.config = file;
        this.vacansy = new ConcurrentHashMap<>();
        this.today = Calendar.getInstance();
        initialize = new Initialize(file);
    }

    /**
     * Received vacancy from site.
     */
    public void toReceiveVacancies() {
        while (true) {
            //получим параметры
            initialize.getParam(config);
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, HH:mm");
            Calendar next = Calendar.getInstance();
            Calendar start = Calendar.getInstance();
            try {
                start.setTime(format.parse(initialize.getStartDate()));
                next.setTime(format.parse(initialize.getNextDate()));
            } catch (ParseException e) {
                LOGGER.error(e.getMessage(), e);
            }
            //если текущая дата больше стартовой (например стартовая - начало года)
            if (today.getTimeInMillis() > start.getTimeInMillis()) {
                work(vacansy, start, initialize.getSiteParsing());
            } else {
                //если текущая дата меньше стартовой
                try {
                    Thread.currentThread().join(next.getTimeInMillis() - start.getTimeInMillis());
                    work(vacansy, start, initialize.getSiteParsing());
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Work method.
     *
     * @param map   map vacancy.
     * @param start start date.
     * @param site  site.
     */
    private void work(ConcurrentHashMap map, Calendar start, String site) {
        ParseSite parseSite = new ParseSite(map, start, site);
        parseSite.parse();
        //получили карту вакансий - пойдем в базу
        if (map.size() != 0) {
            VacansyDataBase dataBase = new VacansyDataBase(map,
                    initialize.getDataBaseURL(),
                    initialize.getUserDataBase(),
                    initialize.getUserPassword());
            dataBase.addNewVacancys();
        }
        initialize.setDateForNextParse();
    }
}
