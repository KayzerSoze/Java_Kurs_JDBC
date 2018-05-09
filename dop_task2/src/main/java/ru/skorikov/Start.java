package ru.skorikov;

import java.io.File;

/**
 * Created by AlexSkorikov on 03.05.18.
 */
public class Start extends Thread {
    /**
     *file config.
     */
    private File file;
    /**
     *Work.
     */
    private Work work;

    /**
     * new Start.
     * @param file config
     * @param work work
     */
    public Start(File file, Work work) {
        this.file = file;
        this.work = work;
    }

    @Override
    public void run() {
        work = new Work(file);
        while (!Thread.interrupted()) {
            work.toReceiveVacancies();
        }
    }
}
