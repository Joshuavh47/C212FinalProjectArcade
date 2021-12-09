/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212;

// importing classes
import edu.iu.c212.models.User;
import edu.iu.c212.models.Item;
import edu.iu.c212.places.Inventory;
import edu.iu.c212.places.Lobby;
import edu.iu.c212.places.Place;

//import com.mycompany.c212final.Item;
//import com.mycompany.c212final.User;
//import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


import edu.iu.c212.places.Store;
import edu.iu.c212.places.games.hangman.HangmanGame;
import edu.iu.c212.utils.FileUtils;

import static edu.iu.c212.utils.FileUtils.writeUserDataToFile;
//import org.apache.commons.io.FileUtils;

/**
 *
 * @author johhe
 */
public class Arcade implements IArcade{
    public User currentUser;
    public List<User> allUsers = new ArrayList<>(); // <User>
    public List<Place> allPlaces = new ArrayList<>(); // <places>
    //File userFile = new File("src/edu/iu/c212/users.txt");
        //src/edu/iu/c212/users.txt
    
    public Arcade() throws IOException{
        initializeAllPlaces(); // all places are initialized and places in list allPlaces
        getUserSaveDataFromFile(); // all users in user.txt are placed in list allUsers
        this.currentUser = getUserOnArcadeEntry(); // due to this, throw exception
        this.allPlaces.get(0).onEnter(this.currentUser);


    }
    // why do we need this method ->
    public void addAllPlaces(List<Place> listOfPlaces){
        this.allPlaces = listOfPlaces;
    }

    public void initializeAllPlaces(){

        // 4 games, lobby, inventory, store
        Lobby lobby = new Lobby("Lobby", this, 0);
        Inventory inventory = new Inventory("Inventory", this, 0);
        Store store = new Store("Store", this, 0);
        HangmanGame hangman = new HangmanGame("Hangman", this, 5);
        //Blackjack blackjack = new HangmanGame("Blackjack", this, 20);
        //TriviaGame trivia = new HangmanGame("Trivia", this, 0);
        //GuessTheNumberGame guessNumber = new HangmanGame("Guess the Number", this, 5);
        // add all places to list

        this.allPlaces.add(lobby);
        this.allPlaces.add(store);
        this.allPlaces.add(inventory);
        this.allPlaces.add(hangman);
        //this.allPlaces.add(blackjack);
        //this.allPlaces.add(trivia);
        //this.allPlaces.add(guessNumber);
    }
    // return index
    public int findPlace(String nameOfPlace){
        for(int i = 0; i < allPlaces.size(); i++){
            if(allPlaces.get(i).placeName.equals(nameOfPlace)){
                return i;
            }
        }
        // will not return null
        return 0;
    }
    
//    public void getAllPlaces(){
//        int counter = 1;
//        for(String i: places.keySet()){
//            System.out.println(counter + "). " + i);
//            counter += 1;
//        }      
//        
//    }

    // do i make get user data
    /**
     * Looks at the user.txt file and adds all the users to the signature
     * List<User> allUsers and then returns the list
     * @return
     * @throws IOException 
     */
    @Override
    public List<User> getUserSaveDataFromFile() throws IOException {
        //getUserDataFromFile -> reads the user.txt file and returns all the users in a list
        this.allUsers = FileUtils.getUserDataFromFile();
        return this.allUsers;


//        List<String> allUsersFromFile = FileUtils.readLines(this.userFile,
//                    StandardCharsets.UTF_8.name());
//        for(int i = 0; i< allUsersFromFile.size(); i++){
//            List<Item> userItems = new ArrayList<Item>();
//            String[] line = allUsersFromFile.get(i).split("|");
//            // check if they have items
//            if(line.length > 2){
//                String[] itemNames = line[3].split(",");
//                for(int j = 0; j < itemNames.length; j++){
//                    // checks for the item in the item class
//                        // method return item or return null
//                        // either way it is added to the list of Items for the user
//                    userItems.add(Item.checkForItem(itemNames[i]));
//                }
//                this.allUsers.add(new User(line[0], Double.parseDouble(line[1]), userItems));
//            }
//            else{
//                this.allUsers.add(new User(line[0], Double.parseDouble(line[1]), null));
//                // user has no items
//            }
//        }
//        return this.allUsers;
    }

    /**
     * look at all users from the list
     * Transfer each username,balance,inventory to a string
     * Write each user to user.txt (overwriting previous file)
     * @throws IOException
     */
    @Override
    public void saveUsersToFile() throws IOException {
        writeUserDataToFile(this.allUsers);
    }

//        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/students.txt"));
//        for(int i = 0; i < this.allUsers.size(); i++){
//            String name = this.allUsers.get(i).getUsername();
//            double balance = this.allUsers.get(i).getBalance();
//            String items = "";
//            if(this.allUsers.get(i).getInventory() != null){
//                int inventoryLength = this.allUsers.get(i).getInventory().size();
//                for(int j = 0; i < inventoryLength; j++){
//                    Item itemObject = this.allUsers.get(i).getInventory().get(j);
//                    items += itemObject.name()+",";
//                }
//            }
//            //remove last comma
//            if(items.length() > 0){
//                items = items.substring(0,items.length()-1);
//            }
//            if(i==0){
//                String userToFile = name + "|" + balance + "|" + items;
//                writer.write(userToFile);
//            }
//            else{
//                String userToFile = "\n" + name + "|" + balance + "|" + items;
//                writer.write(userToFile);
//            }
            
            
            //
            //
            //I think this works, only error would happen when writing to file
            
//            FileUtils.writeStringToFile(this.userFile, userToFile, 
//                StandardCharsets.UTF_8.name());
        //writer.close();

    // this method is not needed right now
    //If the user doesn’t have enough money to go to the place, you should
    //print a warning to the user and transition to the lobby
    //- If the user has enough money to go to the place, you should subtract the
    //entry fee from the player, save to file, and then enter the place
    /**
     * 
     * @param newPlaceNameToGoTo 
     */
    @Override
    public void transitionArcadeState(String newPlaceNameToGoTo) throws IOException {
        for(int i = 0; i< allPlaces.size(); i++){
            if(allPlaces.get(i).placeName.equals(newPlaceNameToGoTo)){
                //Place newPlace = allPlaces.get(i);
                if(allPlaces.get(i).entryFee > currentUser.getBalance()){
                    System.out.println("Error: not enough funds. Try Again");
                    // not enough funds they reenter the lobby to pick a different option
                    allPlaces.get(0).onEnter(currentUser);
                }
                else{
                    // subtract from user balance
                    currentUser.subtractBalance(allPlaces.get(i).entryFee);
                    try {
                        // save changes to user to user.txt
                        saveUsersToFile();
                    } catch (IOException ex) {
                        Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
                    }
                // enter the new place
                allPlaces.get(i).onEnter(currentUser);
                }
                
            }
        }
        
//        if(newPlace.entryFee > currentUser.getBalance()){
//            System.out.println("Error: not enough funds. Try Again");
//            allPlaces.get(0).onEnter(currentUser);
//        }
//        else{
//            // subtract from user balance
//            currentUser.subtractBalance(newPlace.entryFee);
//            try {
//                // save changes to user to user.
//                saveUsersToFile();
//            } catch (IOException ex) {
//                Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            // enter the new place
//            newPlace.onEnter(currentUser);
//        }
//        try {
//            for(int i = 0; i< allPlaces.size(); i++){
//                System.out.println(allPlaces.get(i).toString());
//            }
//            Scanner in = new Scanner(System.in);
//            int choice = in.nextInt()-1; // subtract one for the index
//            if(allPlaces.get(choice).entryFee > currentUser.getBalance()){
//                System.out.println("Error: not enough funds. Try Again");
//            // send back to lobby -> lobby must be the first item in the list
//                allPlaces.get(0).onEnter(currentUser);
//            }
//            else{
//                // subtract from user balance
//                currentUser.subtractBalance(allPlaces.get(choice).entryFee);
//                // save changes to user to user.
//                // enter the new place
//                saveUsersToFile();
//                allPlaces.get(choice).onEnter(currentUser);
//            }
//            // this is implemented in Lobby onEnter
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Arcade.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Error");
//        }
    }
    
    /**
     * views all users from user.txt
     * if username is not found it creates a user and writes that to the user.txt file
     * if user is found, it will read the user details and create a user instance to return
     * All users will be added to the list
     *
     * @return
     * @throws IOException
     */    // need to check since it kinda sucks a user can mis spell a item name
    @Override
    public User getUserOnArcadeEntry() throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("What is your username?");
        String username = in.nextLine();
        // check if username is in allUsers list
        boolean usernameFound = false;
        User foundUser = new User(); // null will be swapped with the found user if found
        for(int i = 0; i < this.allUsers.size(); i++) {
            if(this.allUsers.get(i).getUsername().equals(username)) {
                usernameFound = true;
                foundUser = this.allUsers.get(i);
                break;
            }
        }

        if(usernameFound == true) {
            System.out.println("Welcome back " + foundUser.getUsername());
            return foundUser;
        }
        else {
            System.out.println("How much would you like in your account");
            double balance = in.nextDouble();
            // on enter a user will not have any items
            List<Item> userItems = new ArrayList<>();
            User newUser = new User(username, balance, userItems);
            // add new user to userList
            this.allUsers.add(newUser);
            // send to saveUserToFile -> sends to writeUserDataToFile -> this will overwrite user.txt file when all the users which now includes the newUser
            saveUsersToFile();

            System.out.println("Welcome to the PlayTime");
            return newUser;
        }

//        List<String> allUsersFromFile = FileUtils.readLines(this.userFile,
//                    StandardCharsets.UTF_8.name());
//        boolean found = false;
//        List<Item> userItems = new ArrayList<Item>();
//        User outputUser;
//        for(int i = 0; i< allUsersFromFile.size(); i++){
//            String[] line = allUsersFromFile.get(i).split("|");
//            // checking if user is in the user.txt file
//            if(username.equals(line[0])){
//                found = true;
//                if(line.length > 2){
//                    String[] itemNames = line[3].split(",");
//                    for(int j = 0; j < itemNames.length; j++){
//                        // checks for the item in the item class
//                            // method return item or return null
//                            // either way it is added to the list of Items for the user
//                        userItems.add(Item.checkForItem(itemNames[i]));
//                    }
//                }
//                else{
//                    // user has no items
//                    //userItems = null;
//                }
//                //Creates a user based on the user.txt information
//                outputUser = new User(username, Double.parseDouble(line[1]), userItems);
//                found = true;
//                return outputUser;
//            }
//        }
//
//        if(found == true){
//            System.out.println("Welcome back " + username);
//        }
//        else{
//            // add user to user.txt
//            System.out.println("How much money did you come with?");
//            double balance = in.nextDouble();
//            System.out.println("How many items did you bring to sell? (0,1,2,3)");
//
//            int numOfItems = in.nextInt();
//            List<Item> inventory = new ArrayList<Item>();
//            // allow the users to retype an answer
//            if(numOfItems == 0){
//                //userItems = null;
//                outputUser = new User(username, balance, userItems);
//                String userToFile = "\n" + username + "|"+balance+"|";
//                //write user to file
//                FileUtils.writeStringToFile(this.userFile, userToFile,
//                StandardCharsets.UTF_8.name());
//                System.out.println("Welcome " + username);
//                this.allUsers.add(outputUser);
//                return outputUser;
//            }
//            else{
//                String strItemNameToFile = "";
//                for(int i = 0; i < numOfItems; i++){
//                    System.out.println("What is the Items name?");
//                    String itemName = in.nextLine();
//                    strItemNameToFile += itemName;
//                    userItems.add(Item.checkForItem(itemName));
//                }
//                //write user to file
//                String userToFile = "\n" + username + "|"+balance+"|"+strItemNameToFile;
//                FileUtils.writeStringToFile(this.userFile, userToFile,
//                StandardCharsets.UTF_8.name());
//                outputUser = new User(username, balance, userItems);
//
//                this.allUsers.add(outputUser);
//                System.out.println("Welcome " + username);
//                return outputUser;
//            }
//        }
//        return null; // this will not happen
    }

    @Override
    public List<Place> getAllPlaces() {
        return this.allPlaces;
    }
}
