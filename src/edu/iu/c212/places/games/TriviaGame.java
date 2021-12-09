package edu.iu.c212.places.games;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.games.Game;
import edu.iu.c212.places.games.hangman.HangmanGame;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.http.HttpUtils;
import edu.iu.c212.utils.http.Response;
import edu.iu.c212.utils.http.TriviaQuestion;
import edu.iu.c212.utils.http.TriviaResponse;

import java.io.IOException;
import java.util.List;

public class TriviaGame extends Game {
    User player;

    public TriviaGame(String lobby, Arcade playTime, double cost) {
        super(lobby, playTime, cost);
    }

    @Override
    public void onEnter(User user) {
        int indexOfGame = arcade.findPlace("Trivia");
        Place game = arcade.allPlaces.get(indexOfGame);
        int count = 1;
        int numOfQuestions = 5;
        TriviaGame triviaGame = new TriviaGame(game.placeName, game.arcade, game.entryFee);
        System.out.println("Welcome to C212 trivia. You get $2 for every correct answer - there are 5 total questions in this trivia round");
        for (int i = 0; i < numOfQuestions; i++) {//for loop to print questions
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                List<TriviaQuestion> questions = HttpUtils.getTriviaQuestions(5);
                ConsoleUtils.printMenuToConsole("You're on question" + count + " Ready?",questions, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //print question
            //print shuffled respondces
        }
    }

}