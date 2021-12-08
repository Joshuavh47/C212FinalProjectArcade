package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;

import java.io.IOException;

public class TriviaGame extends Game{
    public TriviaGame(String lobby, Arcade playTime, double cost) {
        super(lobby, playTime, cost);
    }

    @Override
    public void onEnter(User user) throws IOException {

    }

}
//        The user will be guessing the answers to 5 randomly-generated trivia questions. They will get
//        $2 for each correct answer. There will be no entry fee for this game.
//        - @Override void onEnter(User user)
//        - You should use the HttpUtils.getTriviaQuestions method to get the random trivia
//        questions.
//        - For each trivia question:
//        -   You should print “You’re on question #. Ready?” and then wait 1 second
//        before continuing (hint: Thread.sleep).
//        - The answers should be shuffled.
//        - You should use ConsoleUtils.printMenuToConsole to ask the question
//        and get the user selected answer.
//        - If the user gets the correct answer, add $2 to their balance and save.
//        Otherwise, let them know what the correct answer was.
//        - If the user gets 3 or more questions right by the end, print “Nice! You got #
//        questions right.”
//        - Otherwise, print “Aww, good try. You got # questions right.”
//        - Then, go back to the lobby
