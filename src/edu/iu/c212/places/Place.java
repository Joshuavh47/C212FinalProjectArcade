/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212.places;

//import com.mycompany.c212final.User;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johhe
 */
public abstract class Place {
    public String placeName;
    public Arcade arcade;
    public double entryFee;

    public Place(String lobby, Arcade playTime, double cost){
        this.placeName = lobby;
        this.arcade = playTime;
        this.entryFee = cost;
    }

    public abstract void onEnter(User user) throws IOException;
    @Override
    public String toString(){
        boolean game = true;
        List<String> nonGames = new ArrayList<>();
        nonGames.add("Lobby");
        nonGames.add("Inventory");
        nonGames.add("Store");
        // check for game
        if(nonGames.contains(this.placeName)){
            game = false;
        }
        String output = (this.placeName + "(cost: " +this.entryFee + "). Game? " + game);
        return output;
    }
//    public String getName(Place ob){
//        return ob.placeName;
//    }
////    abstract String getName();
    
}

