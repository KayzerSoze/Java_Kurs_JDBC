package ru.skorikov;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public class ConsoleInput implements Input {
    /**
     * Console input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Implementation of the interface Input.
     *
     * @param question question..
     * @return answer.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Displayes question and wait answer from user.
     *
     * @param question question
     * @param range    range of aswers
     * @return answer
     */
    @Override
    public int ask(String question, int[] range) throws MenuOutException {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out from range.");
        }
    }

}
