/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212.models;

/**
 *
 * @author johhe
 */
public enum Item {
    Nachos("Nachos", 4.00),
    Chips("Lays Chips", 1.75),
    Soda("Dr. Pepper", 2.00),
    Headphones("Sony Headphones", 25.50),
    Pizza("Cheese Pizza", 13.50),
    Comic("Marvel Comic", 4.35),
    Cards("Blackjack Card deck", 5.50),
    BouncyBall("Extreme Bouncy Ball", 3.50),
    Poster("Acade Poster", 5.00),
    Charger("Apple Charger", 15.20);
    
    final String readableName;
    double value;

    Item(String readableName, double value){
        this.readableName = readableName;
        this.value = value;
    }
    
    public String showName(){
        return this.readableName;
    }
    public double showPrice(){
        return this.value;
    }
//    @Override public String toString(){
//        String allItems = "[";
//        for(Item i: Item.values()){
//            allItems += "["+i.readableName + ", "+i.value+"]";
//        }
//        allItems += "]";
//        return allItems;
//    }
    // user could misspell an item name
    public static Item checkForItem(String item){
        for(Item i: Item.values()){
            if(i.readableName.equals(item)){
                // returns item, useful for comparing values in user txt file
                return i;
            }
        }
        return null;
    }
}
