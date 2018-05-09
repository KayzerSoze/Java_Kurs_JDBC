package ru.skorikov;

import org.junit.Test;

import java.io.File;

/**
 * Created by AlexSkorikov on 08.05.18.
 */
public class StartTest {
    /**
     * Try run programm.
     */
    @Test
    public void tryStartProgramm() {
        File file = new File("src/main/resources/config.xml");
        Work work = new Work(file);
        Thread thread = new Start(file, work);
        thread.start();
        try {
            thread.join(1000 * 60 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}