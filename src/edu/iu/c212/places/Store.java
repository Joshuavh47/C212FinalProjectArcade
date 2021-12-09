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
import edu.iu.c212.utils.ConsoleUtils;

import java.io.IOException;
import java.util.ArrayList;
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
        List<String> yesNo = new ArrayList<>();
        yesNo.add("Yes");
        yesNo.add("No");
        List<StoreAction> options = List.of(StoreAction.values());
        boolean done = false;
        Scanner in = new Scanner(System.in);
        while(done == false){
            StoreAction choice = ConsoleUtils.printMenuToConsole("Welcome to the Store", options, true);
            if(choice.name().equals("SELL")){
                if(user.getInventory().size() == 0){
                    System.out.println("You may not sell anything (Inventory empty)");
                }
                else{
                    Item choiceItem = ConsoleUtils.printMenuToConsole(user.getUsername() + "'s items", user.getInventory(), true);
                    System.out.println("Warning you will only get 50% of the Item value back");
                    String finalDecision = ConsoleUtils.printMenuToConsole("Final Decision to Sell", yesNo, true);
                    if (finalDecision.equals("Yes")){
                        // deletes the item, adds to balance, and saves this users new balance
                        user.sellItem(choiceItem);
                        arcade.saveUsersToFile();
                    }
                }
            }
            else if(choice.name().equals("BUY")){
                Item choiceItem = (Item) ConsoleUtils.printMenuToConsole("Select an Item to buy", List.of(Item.values()), true);
                if(choiceItem.showPrice()> user.getBalance() | user.getInventory().size() > 3){
                    System.out.println("Unable to purchase Item");
                }
                else{
                    String finalDecision = ConsoleUtils.printMenuToConsole("Final Decision to Buy", yesNo, true);
                    if (finalDecision.equals("Yes")){
                        // adds the item, subtracts from balance, and saves this users new balance
                        user.addItem(choiceItem);
                        arcade.saveUsersToFile();
                    }

                }
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
