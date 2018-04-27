package ru.skorikov;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public interface UserAction {
    /**
     * The key to access the execute method.
     *
     * @return key.
     */
    int key();

    /**
     * Метод.
     *
     * @param input input.
     * @param tracker tracker.
     */
    void execute(Input input, Tracker tracker);

    /**
     * String on screen.
     *
     * @return string.
     */
    String info();

}
