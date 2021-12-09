package edu.iu.c212.places.games.hangman;

import edu.iu.c212.utils.http.HttpUtils;

import java.io.IOException;

public class HangmanWord {
    public static String getWord() {
        try {
            return HttpUtils.getRandomHangmanWord();
        } catch (IOException e) {
            System.out.println("Error getting word");
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
