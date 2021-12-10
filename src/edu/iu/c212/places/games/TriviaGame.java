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
import java.util.Collections;
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
//        int count = 1;
//        int numOfQuestions = 5;
        System.out.println("Welcome to C212 trivia. You get $2 for every correct answer - there are 5 total questions in this trivia round");
        for (int i = 0; i < numOfQuestions; i++) {//for loop to print questions

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> resp = questions.get(i).getIncorrectAnswers();
            System.out.println(questions.get(i).getQuestion());
            resp.add(questions.get(i).getCorrectAnswer());
            Collections.shuffle(resp);
            ConsoleUtils.printMenuToConsole("You're on question" + count + " Ready?",resp, true);


            //print question
            //print shuffled respondces
        }
    }

//    public static void main(String[] args) {
//        List<TriviaQuestion> questions = null;
//        try {
//            questions = HttpUtils.getTriviaQuestions(5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int numOfQuestions = 5;
//        for (int i = 0; i < numOfQuestions; i++) {//for loop to print questions
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            List<String> resp = questions.get(i).getIncorrectAnswers();
//            System.out.println(questions.get(i).getQuestion());
//            resp.add(questions.get(i).getCorrectAnswer());
//            Collections.shuffle(resp);
//            ConsoleUtils.printMenuToConsole("You're on question" + i + " Ready?", resp, true);

//        try {
//              int count = 1;
//            List<TriviaQuestion> questions = HttpUtils.getTriviaQuestions(5);
//            List<String> r = questions.get(0).getIncorrectAnswers();
//            System.out.println(questions.get(0).getQuestion());
//            r.add(questions.get(0).getCorrectAnswer());
//            Collections.shuffle(r);
//            System.out.println("Answer");
//            System.out.println(questions.get(0).getCorrectAnswer());
//            System.out.println(r);
//            List<String> questions1;
//            ConsoleUtils.printMenuToConsole("You're on question " + count + " Ready?",r, true);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        }
    }}
