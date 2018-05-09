package ru.skorikov;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by AlexSkorikov on 07.05.18.
 */
public class VacansyDataBase {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Work.class);
    /**
     * Vacancys Map.
     */
    private ConcurrentHashMap<String, Vacansy> vacancy;
    /**
     * DataBase URL.
     */
    private String urlDB;
    /**
     * DataBase User.
     */
    private String userDB;
    /**
     * DataBase user password.
     */
    private String userPass;

    /**
     * new Vacancy DataBase.
     * @param vacancy vacansy map.
     * @param urlDB url DB
     * @param userDB user DB
     * @param userPass user password
     */
    VacansyDataBase(ConcurrentHashMap<String, Vacansy> vacancy, String urlDB, String userDB, String userPass) {
        this.vacancy = vacancy;
        this.urlDB = urlDB;
        this.userDB = userDB;
        this.userPass = userPass;
    }

    /**
     * Add new vacancys to DataBase.
     */
    public void addNewVacancys() {
        DataBaseUtil util = new DataBaseUtil(this.urlDB, this.userDB, this.userPass);
        util.dataBaseInit();
        int addedVacan = 0;
        for (Map.Entry<String, Vacansy> map : vacancy.entrySet()) {
            String url = map.getKey();
            String message = map.getValue().getTitle();
            Calendar date = map.getValue().getDateVacancy();
            if (util.addVacansyToDataBase(new Vacansy(url, message, date))) {
                addedVacan++;
            }
        }
        LOGGER.info(String.format("%s %s %s", "Added ", addedVacan, " vacancies."));
    }
}
