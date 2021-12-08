/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iu.c212.places;

//import com.mycompany.c212final.User;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

/**
 *
 * @author johhe
 */
public class Store extends Place{
    
    public Store(String name, Arcade playTime, double cost) {
        super(name, playTime, cost);
    }
    
    @Override
    public void onEnter(User user) {
        
    }
}
