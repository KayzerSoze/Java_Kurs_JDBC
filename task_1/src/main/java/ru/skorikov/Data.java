package ru.skorikov;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 *
 * @ author: Alex_Skorikov.
 * @ date: 21.04.18
 * @ version: Java_Kurs_JDBC
 */

@XmlType(name = "entry")
public class Data {
    /**
     * Data.
     */
    private Long data;

    /**
     * Set Data.
     * @param data data.
     */
    public void setData(Long data) {
        this.data = data;
    }

    /**
     * Get Data.
     * @return data.
     */
    @XmlElement(name = "Data")
    public Long getData() {
        return data;
    }
}

