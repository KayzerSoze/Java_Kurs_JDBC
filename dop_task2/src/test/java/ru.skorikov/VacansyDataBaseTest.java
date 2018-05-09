package ru.skorikov;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by AlexSkorikov on 08.05.18.
 */
public class VacansyDataBaseTest {
    /**
     * new VacancyDB.
     */
    @Test(expected = NullPointerException.class)
    public void addNewVacancys() {
        ConcurrentHashMap map = new ConcurrentHashMap();
        String urlDB = "";
        String user = "";
        String pass = "";

        VacansyDataBase dataBase = new VacansyDataBase(map, urlDB, user, pass);
        dataBase.addNewVacancys();
    }
}