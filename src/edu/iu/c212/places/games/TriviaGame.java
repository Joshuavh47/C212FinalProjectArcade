package edu.iu.c212.places.games;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.http.HttpUtils;
import edu.iu.c212.utils.http.Response;
import edu.iu.c212.utils.http.TriviaQuestion;
import edu.iu.c212.utils.http.TriviaResponse;

import java.io.IOException;
import java.util.List;

public class TriviaGame extends Game {
    User player;
    int count = 1;
    int numOfQuestions = 5;
    Response response;
    TriviaQuestion triviaQuestion;
    TriviaResponse triviaResponse;
    List<TriviaQuestion> questions;

    {
        try {
            questions = HttpUtils.getTriviaQuestions(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TriviaGame(String lobby, Arcade playTime, double cost) {
        super(lobby, playTime, cost);
        this.player = playTime.currentUser;
    }

    @Override
    public void onEnter(User user) {
        User player = user;
        int indexOfGame = arcade.findPlace("Trivia");
        Place game = arcade.allPlaces.get(indexOfGame);
        TriviaGame triviaGame = new TriviaGame(game.placeName, game.arcade, game.entryFee);
        System.out.println(triviaGame.questions.get(0));
//        int count = 1;
//        int numOfQuestions = 5;
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
//
//    public static void main(String[] args) {
//        try {
//            int count = 1;
//            List<TriviaQuestion> questions = HttpUtils.getTriviaQuestions(5);
//            String a = questions.get(1).getQuestion();
//            List<String> r = questions.get(1).getIncorrectAnswers();
//            r.add(questions.get(1).getCorrectAnswer());
//            System.out.println(r);
//            List<String> questions1;
//            System.out.println(a);
//            //ConsoleUtils.printMenuToConsole("You're on question " + count + " Ready?",a, true);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
