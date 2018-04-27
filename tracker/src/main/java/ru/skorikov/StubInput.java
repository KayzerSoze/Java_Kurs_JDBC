package ru.skorikov;

/*
  Created with IntelliJ IDEA.

  @author: Alex Skorikov.
 * @date: 26.04.18
 */

/**
 * The class implements the Input interface.
 */
public class StubInput implements Input {
    /**
     * Array answers.
     */
    private String[] answers;
    /**
     * Array counter.
     */
    private int position = 0;

    /**
     * Consstructor.
     *
     * @param answers array answers.
     */
    StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Class method.
     * Takes the question of the program.
     * Returns the response from the array.
     *
     * @param question question out.
     * @return answer..
     */
    public String ask(String question) {
        return answers[position++];
    }

    @Override
    public int ask(String question, int[] rahge) {
        return Integer.valueOf(answers[position++]);
    }
}
