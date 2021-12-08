/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212.places;

//import com.mycompany.c212final.User;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

import java.util.*;

/**
 *
 * @author johhe
 */
public class Lobby extends Place{

    public Lobby(String lobby, Arcade playTime, double cost) {
        super(lobby, playTime, cost);

    }

    @Override
    public void onEnter(User user) {
        System.out.println("Welcome to the C212 arcade, " + user.getUsername() + "! You're curently in the lobby. "
                + "Your balance is: " + user.getBalance() + ". Where do you want to go next?");
        
        User player = user;
        List<Place> allPlaces = this.arcade.getAllPlaces();
        int counter = 1;
        // print the lobby
        
        for(int i = 0; i< allPlaces.size(); i++){
            System.out.println(counter + ": " + allPlaces.get(i).toString());
            counter += 1;
        }
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt()-1; // subtract one for the index
        String selection = allPlaces.get(choice).placeName;
        
        arcade.transitionArcadeState(selection);
               
    }    
}
