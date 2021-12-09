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
import java.util.Map;

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
            Map<Item,Integer> quantity = new HashMap<Item,Integer>();
            double itemBalance = 0;
            for(int i = 0; i < usersItems.size(); i++){
                //System.out.println(Item.valueOf(usersItems.get(i).name()) + " ");
                // checks if item is in hashmap
                if(quantity.containsKey(Item.valueOf(usersItems.get(i).name()))){
                    // if it is then add one to quantity
                    int quant = quantity.get(Item.valueOf(usersItems.get(i).name()));
                    quantity.remove(Item.valueOf(usersItems.get(i).name()));
                    quantity.put(Item.valueOf(usersItems.get(i).name()), quant + 1);
                    itemBalance += usersItems.get(i).showPrice();
                }
                else{
                    quantity.put(Item.valueOf(usersItems.get(i).name()), 1);
                    itemBalance += usersItems.get(i).showPrice();
                }
            }
            for (Map.Entry<Item, Integer> i : quantity.entrySet()) {
                System.out.println(i.getKey() + ": " + i.getValue() +" (value: " + i.getKey().showPrice() * i.getValue() + ")");
            }
            double totalBalance = arcade.currentUser.getBalance() + itemBalance;
            System.out.println("Total net worth: " + totalBalance);
            if(usersItems.size() == 3){
                System.out.println("REMEMBER! You can only have 3 items at a time. Sell one by going to the Store");
            }
        }
        arcade.allPlaces.get(0).onEnter(user);
    }
    
}
