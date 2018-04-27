package ru.skorikov;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
class StartUI {
    /**
     * Range of menu values.
     */
    private int[] range;

    /**
     * Input method.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;

    /**
     * Menu.
     */
    private MenuTracker menu;

    /**
     * Constructor.
     *
     * @param input   Input interface.
     * @param tracker tracker.
     */
    StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Initialize the application.
     *
     * @throws MenuOutException exception.
     */
    public void init() throws MenuOutException {
        menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        range = menu.getRangeOfUserActions();

        do {
            menu.show();
            menu.select(input.ask("Select:", range));
        } while ((!"y".equals(this.input.ask("Exit: ? y/n"))));
    }

    /**
     * Start application.
     *
     * @param args string array.
     * @throws MenuOutException exception.
     */
    public static void main(String[] args) throws MenuOutException {

        File config = new File("tracker/src/main/resources/config.xml");

        /*
          Input as ConsoleInput.
         */
        Input input = new ValidateInput();
        /*
          new Tracer.
         */
        Tracker tracker = new Tracker(config);
        /*
          StartUI get input, tracker end run init.
         */
        new StartUI(input, tracker).init();
    }
}
