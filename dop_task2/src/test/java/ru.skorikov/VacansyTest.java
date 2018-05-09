package ru.skorikov;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;


/**
 * Created by AlexSkorikov on 07.05.18.
 */
public class VacansyTest {
    /**
     * New classVacansi.
     */
    @Test
    public void thenGetParamWhenReturnParam() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH, 2);
        Vacansy vacansy = new Vacansy("new URL", "new Message", calendar);
        Vacansy vacansy1 = new Vacansy("title", calendar);
        Assert.assertThat(vacansy.getUrl(), is("new URL"));
        Assert.assertThat(vacansy.getTitle(), is("new Message"));
        Assert.assertThat(vacansy.getDateVacancy(), is(calendar));
        Assert.assertThat(vacansy1.getTitle(), is("title"));
        Assert.assertThat(vacansy1.getDateVacancy(), is(calendar));
    }
}