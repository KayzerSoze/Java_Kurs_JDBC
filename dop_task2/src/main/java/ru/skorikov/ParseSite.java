package ru.skorikov;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;


/**
 * Created by AlexSkorikov on 04.05.18.
 */
public class ParseSite {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ParseSite.class);
    /**
     * Parsing site.
     */
    private ParseSiteUtil util = new ParseSiteUtil();
    /**
     * Map vacansies.
     */
    private ConcurrentHashMap<String, Vacansy> vacansyFromSite;
    /**
     * Calendar.
     */
    private Calendar start;
    /**
     * Site URL.
     */
    private String url;

    /**
     * New Parse site.
     * @param vacansy find vacansies.
     * @param start start Date.
     * @param url URl.
     */
    ParseSite(ConcurrentHashMap vacansy, Calendar start, String url) {
        this.vacansyFromSite = vacansy;
        this.start = start;
        this.url = url;
    }

    /**
     * Method parse.
     */
    public void parse() {
        LOGGER.info("Start parse site.");
        int pages = util.getAllPages(url);
        ExecutorService service = Executors.newFixedThreadPool(8);
        List<Future<ConcurrentHashMap>> futureList = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            Callable<ConcurrentHashMap> callable = new ParsePage(util.getPageForParse(url, i), start);
            Future<ConcurrentHashMap> future = service.submit(callable);
            futureList.add(future);
        }
        for (Future<ConcurrentHashMap> futer : futureList) {
            try {
                vacansyFromSite.putAll(futer.get());
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (ExecutionException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        service.shutdown();
        LOGGER.info("Find " + vacansyFromSite.size() + " vacansies");
    }
}











