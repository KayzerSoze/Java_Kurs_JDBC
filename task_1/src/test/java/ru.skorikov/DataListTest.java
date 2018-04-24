package ru.skorikov;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;


/**
 * Created with IntelliJ IDEA.
 *
 * @ author: Alex_Skorikov.
 * @ date: 24.04.18
 * @ version: Java_Kurs_JDBC
 */
public class DataListTest {
    /**
     * Try create DataList.
     */
    @Test
    public void createSetGetDataList() {
        Data data = new Data();
        data.setData(123L);
        ArrayList<Data> list = new ArrayList<>();
        list.add(data);
        DataList dataList = new DataList();
        dataList.setDataList(list);
        Assert.assertThat(dataList.getDataList().get(0).getData(), is(123L));
    }
}