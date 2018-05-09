package ru.skorikov;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;

/**
 * Created by AlexSkorikov on 08.05.18.
 */
public class InitializeTest {
    /**
     * new class initialize.
     */
    @Test
    public void getDataBaseURL() {
        File config = new File("src/main/resources/config.xml");
        Initialize initialize = new Initialize(config);
        initialize.getParam(config);
        Assert.assertThat(initialize.getDataBaseURL(), is("jdbc:postgresql://localhost:5432/Vacancy"));
        Assert.assertThat(initialize.getUserDataBase(), is("postgres"));
        Assert.assertThat(initialize.getUserPassword(), is("postgres"));
        Assert.assertThat(initialize.getSiteParsing(), is("http://www.sql.ru/forum/job-offers"));

    }
}