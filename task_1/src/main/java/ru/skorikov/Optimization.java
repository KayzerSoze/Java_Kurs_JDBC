package ru.skorikov;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * Created by AlexSkorikov on 29.04.18
 */
public class Optimization implements Serializable {
    /**
     * URL DataBase.
     */
    private String url;
    /**
     * User DataBase.
     */
    private String username;
    /**
     * User password.
     */
    private String password;
    /**
     * size DataBase.
     */
    private Integer n;
    /**
     * Ferst fail XML.
     */
    private File file1;
    /**
     * Second File XML.
     */
    private File file2;
    /**
     * File for translate XML.
     */
    private File soursexslt;

    /**
     * Get URL DataBase.
     *
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set url DataBase.
     *
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get Username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set Username.
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password.
     *
     * @param password password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get size database.
     *
     * @return size
     */
    public Integer getN() {
        return n;
    }

    /**
     * Set size database.
     *
     * @param n size
     */
    public void setN(Integer n) {
        this.n = n;
    }

    /**
     * Get inputFile.
     *
     * @return file XML1
     */
    public File getFile1() {
        return file1;
    }

    /**
     * Set inputFile.
     *
     * @param file1 file XML1.
     */
    public void setFile1(File file1) {
        this.file1 = file1;
    }

    /**
     * Get outputFile.
     *
     * @return file XML2
     */
    public File getFile2() {
        return file2;
    }

    /**
     * Set outputFile.
     *
     * @param file2 file XML2
     */
    public void setFile2(File file2) {
        this.file2 = file2;
    }

    /**
     * Get file-translate.
     *
     * @return file
     */
    public File getSoursexslt() {
        return soursexslt;
    }

    /**
     * Set file-translate.
     *
     * @param soursexslt file.
     */
    public void setSourseXslt(File soursexslt) {
        this.soursexslt = soursexslt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Optimization that = (Optimization) o;
        return Objects.equals(url, that.url)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, username, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Method get all results.
     *
     * @throws SQLException         exception
     * @throws TransformerException exception
     */
    public void work() throws SQLException, TransformerException {
        OptimizationUtils utils = new OptimizationUtils();
        utils.createTable(this);
        utils.createXML1(this);
        utils.convertXML1ToXML2(this);
        utils.parseXMLAndReturnResult(this);
    }
}
