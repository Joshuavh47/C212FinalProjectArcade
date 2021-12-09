/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212.places;

//import com.mycompany.c212final.User;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author johhe
 */
public class Inventory extends Place{

    public Inventory(String name, Arcade playTime, double cost) {
        super(name, playTime, cost);

    }
    
    
    @Override
    public void onEnter(User user) throws IOException {
        System.out.println("Hi, " + arcade.currentUser.getUsername() + "! Your inventory looks like this:");
        List<Item> usersItems = arcade.currentUser.getInventory();
        //HashMap<Item,Integer> listOfItems
        if(usersItems.size() == 0){
            System.out.println("You have no current Items");
            System.out.println("Total net worth: " + arcade.currentUser.getBalance());
        }
        else{
            double itemBalance = 0;
            for(int i = 0; i < usersItems.size(); i++){
                System.out.println(Item.valueOf(usersItems.get(i).name()));
                itemBalance += usersItems.get(i).showPrice();
            }
            System.out.println("Total net worth: " + arcade.currentUser.getBalance() + itemBalance);
            if(usersItems.size() == 3){
                System.out.println("REMEMBER! You can only have 3 items at a time. Sell one by going to the Store");
            }
        }
        arcade.allPlaces.get(0).onEnter(user);
    }
    
}
