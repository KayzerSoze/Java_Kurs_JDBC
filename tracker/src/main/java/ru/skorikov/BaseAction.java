package ru.skorikov;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public abstract class BaseAction implements UserAction {
    /**
     * Name.
     */
    private String name;
    /**
     * Key.
     */
    private int key;

    /**
     * Get key.
     *
     * @return key
     */
    public int key() {
        return key;
    }

    /**
     * Get name.
     *
     * @return name.
     */

    String name() {
        return name;
    }

    /**
     * Realization interface UserAction.
     *
     * @return string from key and name
     */
    public String info() {
        return String.format("%s.\t %s", this.key, this.name);
    }

    /**
     * Constructor.
     *
     * @param name name.
     * @param key  key.
     */
    BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

}
