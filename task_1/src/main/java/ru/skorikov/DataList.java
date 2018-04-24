package ru.skorikov;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @ author: Alex_Skorikov.
 * @ date: 22.04.18
 * @ version: Java_Kurs_JDBC
 */
@XmlRootElement(name = "dataList")
public class DataList {
    /**
     * DataList.
     */
    private ArrayList dataList = new ArrayList();

    /**
     * Get DataList.
     * @return dataList.
     */
    public ArrayList<Data> getDataList() {
        return dataList;
    }

    /**
     * Set dataList.
     * @param dataList dataList
     */
    @XmlElement(name = "element")
    public void setDataList(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

}