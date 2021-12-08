package edu.iu.c212.places.games.hangman;

import java.util.*;

/**
 *
 * @author johhe
 */
public interface IHangmanGame {
    /**
     * This will blur out all letters in the world that have not been guessed
     *      using ***********
     * @param guesses
     * @return
     */
    String getBlurredWord();

    /**
     * This will get the lexicon of allowable input characters for the hangman
     *      (should be all lowercase alphabetical characters
     * @return
     */
    List<Character> getValidLexicon();
}
