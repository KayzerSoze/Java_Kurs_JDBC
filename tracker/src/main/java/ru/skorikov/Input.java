package ru.skorikov;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public interface Input {
    /**
     * Get a string parameter and return answer.
     *
     * @param question question
     * @return response fron User.
     */
    String ask(String question);

    /**
     * Get string parameter and range values.
     *
     * @param question question.
     * @param rahge    range values.
     * @return answer
     * @throws MenuOutException exception.
     */
    int ask(String question, int[] rahge) throws MenuOutException;

}
