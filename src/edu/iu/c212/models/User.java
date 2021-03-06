package edu.iu.c212.models;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.mycompany.c212final;

import java.util.*;

/**
 *
 * @author johhe
 */
public class User {

    String username;
    double balance;
    List<Item> inventory;// = new ArrayList<Item>();

    public User(String username, double balance, List<Item> inventory){
        this.username = username;
        this.balance = balance;
        this.inventory = inventory;
    }
    public User(){}

    public void subtractBalance(double amount){
        if(amount > this.balance){
            System.out.println("Do not have enough money");
        }
        else{
            this.balance -= amount;
        }
    }
    public String getUsername(){
        return this.username;
    }
    public double getBalance(){
        return this.balance;
    }
    public List<Item> getInventory(){
        return this.inventory;
    }
    public void addBalance(double amount){
        this.balance += amount;
    }

    public void addItem(Item newItem){
        this.balance -= newItem.showPrice();
        this.inventory.add(newItem);
    }
    public void sellItem(Item newItem){
        this.inventory.remove(newItem);
        this.balance += newItem.showPrice();
    }
    public String inventoryToString(){
        if(this.inventory.size() == 0){
            return "";
        }
        else{
            String inv = "";
            for(int i = 0; i < this.inventory.size(); i++){
                inv += inventory.get(i).name();
                inv += ",";
            }
            inv = inv.substring(0,inv.length()-1);
            return inv;
        }
    }
}


