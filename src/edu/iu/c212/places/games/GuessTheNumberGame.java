package edu.iu.c212.places.games;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Lobby;
import edu.iu.c212.places.Place;
import edu.iu.c212.utils.ConsoleUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GuessTheNumberGame extends Game{
    int count = 0;
    User player;
    String failureMessage = "What's your guess?";
    int randomNum = (int) (Math.random() * (200));

    public GuessTheNumberGame(String lobby, Arcade playTime, double cost) {
        super(lobby, playTime, cost);
    }

    @Override
    public void onEnter(User user) throws IOException {
        player = user;
        int indexOfGame = arcade.findPlace("GuessTheNumber");
        Place game = arcade.allPlaces.get(indexOfGame);
        GuessTheNumberGame gn = new GuessTheNumberGame(game.placeName, game.arcade, game.entryFee);

        System.out.println("Welcome to Guess The Number. You'll be guessing a number between 0 and 200");
        System.out.println("You'll get $10 if you correctly guess the number within 5 tries. Otherwise, you get nothing");
        System.out.println(failureMessage);

        Function<Integer, Boolean> condition = integer -> {
            if (count < 4) {
                count++;
                if (integer == randomNum) {
                    System.out.println("Congratulations, you correctly guessed the number!");
                    System.out.println("You guessed it within 5 tries, so you get $10");
                    player.addBalance(10.00);
                    return true;
                } else {
                    System.out.println("Oh no, you didn't guess correctly.");
                    if (integer > randomNum) System.out.println("Your number was too high");
                    else System.out.println("Your number was too low");
                    return false;
                }
            }
            else {
                System.out.println("Oh no, you didn't guess correctly.");
                System.out.println("You were unable to guess the number in 5 tries, so you get nothing");
                return true;
            }
        };
        ConsoleUtils.readIntegerLineFromConsoleOrElseComplainAndRetry(condition,failureMessage);
        arcade.saveUsersToFile();
        arcade.getAllPlaces().get(0).onEnter(user);

        }

}
