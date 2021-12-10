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
    String failureMessage;
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

        Function<Integer, Boolean> condition = integer -> {
            count++;
            failureMessage = "";
            if (integer == randomNum){
                System.out.println("Congratulations, you correctly guessed the number!");
                return true;
            }
            else {
                failureMessage = "Oh no, you didn't guess correctly.\n";
                if (integer > randomNum) failureMessage += "Your number was too high";
                else failureMessage += "Your number was too low";
                failureMessage+="\nWhat's your guess?";
                return false;
            }
        };

        ConsoleUtils.readIntegerLineFromConsoleOrElseComplainAndRetry(condition,failureMessage);
        if(count<5){
            System.out.println("You guessed it within 5 tries, so you get $10");
            player.addBalance(10.00);
            arcade.saveUsersToFile();
        }
        else System.out.println("You took more than 5 tries, so you get nothing");

        arcade.transitionArcadeState("lobby");
    }

  /*  public static void main(String[] args) throws IOException{
        Arcade a = new Arcade();
        User player = a.currentUser;
        GuessTheNumberGame guessNumber = new GuessTheNumberGame("Guessthenumber", a, 5);
        Lobby lobby = new Lobby("Lobby", a, 0);
        List allPlaces = new ArrayList<Place>();
        allPlaces.add(guessNumber);
        allPlaces.add(lobby);
        a.addAllPlaces(allPlaces);
        guessNumber.onEnter(player);
    } */
}