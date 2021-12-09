/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212;

//import com.mycompany.c212final.User;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Inventory;
import edu.iu.c212.places.Lobby;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.Store;
import edu.iu.c212.places.games.hangman.HangmanGame;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author johhe
 */
public class ArcadeMain {
    public static void main(String[] args) throws IOException {
        // handles the arcade
        Arcade playTime = new Arcade();
        //User player = playTime.currentUser;
        // all places are initialized in -> initializeAllPlaces

        // Create instances of all places to create the Arcade
                    // 4 games, lobby, inventory, store
            //        Lobby lobby = new Lobby("Lobby", playTime, 0);
            //        Inventory inventory = new Inventory("Inventory", playTime, 0);
            //        Store store = new Store("Store", playTime, 0);
            //        HangmanGame hangman = new HangmanGame("Hangman", playTime, 5);


                    //Blackjack blackjack = new Blackjack();
                    // add Trivia
                    // add Guess a number
                    // add all places to a list then add that to the Arcade (playtime)
            //        List allPlaces = new ArrayList<Place>();
            //        allPlaces.add(lobby);
            //        allPlaces.add(store);
            //        allPlaces.add(inventory);
            //        allPlaces.add(hangman);
            //        allPlaces.add(blackjack);
            //        allPlaces.add(trivia);
            //        allPlaces.add(guessNumber);

                    // add allPlaces to the arcade
            //        playTime.addAllPlaces(allPlaces);
        
        
        
        //hangman.onEnter(player);
        
        // displaces the arcade menu // then directs user to next location
        //lobby.onEnter(player);
        //playTime.transitionArcadeState();
        
        
    }
}
