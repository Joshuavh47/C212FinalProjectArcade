/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212;

//import com.mycompany.c212final.User;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author johhe
 */
public interface  IArcade {
    List<User> getUserSaveDataFromFile() throws IOException;
//- This will call FileUtils.getUserDataFromFile, and use System.exit to exit the
//program if an exception is thrown.
    void saveUsersToFile() throws IOException;
//- This should call FileUtils.writeUserDataToFile to write all users to the txt file
    void transitionArcadeState(String newPlaceNameToGoTo);
//- This should try to transition the currentUser to the new place.
//- If the user doesn’t have enough money to go to the place, you should
//print a warning to the user and transition to the lobby
//- If the user has enough money to go to the place, you should subtract the
//entry fee from the player, save to file, and then enter the place
    User getUserOnArcadeEntry() throws IOException;
//- This should ask for a username to be entered.
//- If the username is not contained in the users as read from users.txt, a
//new user must be created and saved to users.txt. A welcome message
//should be printed
//- If the username already exists, a welcome back message should be
//printed
    List<Place> getAllPlaces();
}

