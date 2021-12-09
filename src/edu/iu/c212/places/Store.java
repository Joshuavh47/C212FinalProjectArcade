/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212.places;

//import com.mycompany.c212final.User;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author johhe
 */
public class Store extends Place{
    public enum StoreAction {
        BUY, SELL, LEAVE;
        @Override
        public String toString() {
          return this.name().toLowerCase();
        }
    }
    public Store(String name, Arcade playTime, double cost) {
        super(name, playTime, cost);
    }
    
    @Override
    public void onEnter(User user) throws IOException {
        List<StoreAction> options = List.of(StoreAction.values());
        boolean done = false;
        Scanner in = new Scanner(System.in);
        while(done == false){
            StoreAction choice = ConsoleUtils.printMenuToConsole("Welcome to the Store", options, true);
            if(choice.name().equals("SELL")){

            }
            else if(choice.name().equals("BUY")){

            }
            else{
                done = true;
                break;
            }
        }
        // brings back to lobby
        this.arcade.getAllPlaces().get(0).onEnter(user);
    }




}
