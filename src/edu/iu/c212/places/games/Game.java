package edu.iu.c212.places.games;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.mycompany.c212final.Games;

//import com.mycompany.c212final.User;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

/**
 *
 * @author johhe
 */
public abstract class Game extends Place {

    public Game(String lobby, Arcade playTime, double cost) {

        super(lobby, playTime, cost);
    }
}

    //abstract onEnter(User user);
