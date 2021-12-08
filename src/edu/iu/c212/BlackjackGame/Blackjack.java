package edu.iu.c212.BlackjackGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    private ArrayList<String> cardsArray = new ArrayList<String>();
    private int cardsLeft;
    private HashMap<String, Integer> cardsValues = new HashMap<String, Integer>();
    private int[] playerTotals=new int[2];
    private int[] dealerTotals=new int[2];
    private boolean busted = false;
    private boolean stay=false;
    private ArrayList<String> playerCards = new ArrayList<String>();
    private ArrayList<String> dealerCards = new ArrayList<String>();
    private boolean twentyOne = false;
    private boolean blackjack = false;
    public Blackjack(){
        cardsArray.add("A");
        cardsArray.add("A");
        cardsArray.add("A");
        cardsArray.add("A");
        cardsArray.add("2");
        cardsArray.add("2");
        cardsArray.add("2");
        cardsArray.add("2");
        cardsArray.add("3");
        cardsArray.add("3");
        cardsArray.add("3");
        cardsArray.add("3");
        cardsArray.add("4");
        cardsArray.add("4");
        cardsArray.add("4");
        cardsArray.add("4");
        cardsArray.add("5");
        cardsArray.add("5");
        cardsArray.add("5");
        cardsArray.add("5");
        cardsArray.add("6");
        cardsArray.add("6");
        cardsArray.add("6");
        cardsArray.add("6");
        cardsArray.add("7");
        cardsArray.add("7");
        cardsArray.add("7");
        cardsArray.add("7");
        cardsArray.add("8");
        cardsArray.add("8");
        cardsArray.add("8");
        cardsArray.add("8");
        cardsArray.add("9");
        cardsArray.add("9");
        cardsArray.add("9");
        cardsArray.add("9");
        cardsArray.add("10");
        cardsArray.add("10");
        cardsArray.add("10");
        cardsArray.add("10");
        cardsArray.add("J");
        cardsArray.add("J");
        cardsArray.add("J");
        cardsArray.add("J");
        cardsArray.add("Q");
        cardsArray.add("Q");
        cardsArray.add("Q");
        cardsArray.add("Q");
        cardsArray.add("K");
        cardsArray.add("K");
        cardsArray.add("K");
        cardsArray.add("K");
        cardsLeft=cardsArray.size();
        cardsValues.put("A",1);
        cardsValues.put("2",2);
        cardsValues.put("3",3);
        cardsValues.put("4",4);
        cardsValues.put("5",5);
        cardsValues.put("6",6);
        cardsValues.put("7",7);
        cardsValues.put("8",8);
        cardsValues.put("9",9);
        cardsValues.put("10",10);
        cardsValues.put("J",10);
        cardsValues.put("Q",10);
        cardsValues.put("K",10);
        run();
    }

    public void run(){
        System.out.println("Welcome to Blackjack! $20/game to play, $50 every win.\nType \"Play\" to play, or \"Back\" to return back to the main menu.");
        Scanner strScanner = new Scanner(System.in);
        String play=strScanner.nextLine();
        if(play.equalsIgnoreCase("play")) {
            dealerHit();
            hit();
            hit();
            while (!busted && !stay&&!twentyOne&&!blackjack) {

                if(playerCards.size()==2&&(playerTotals[0]==21||playerTotals[1]==21)){
                    dealerHit();
                    while(!play.equalsIgnoreCase("yes")&&!play.equalsIgnoreCase("no")) {
                        printCards();
                        System.out.print("Blackjack! Play again? (Yes/No): ");
                        play = strScanner.nextLine();
                        if (play.equalsIgnoreCase("yes")) {
                            new Blackjack();
                        } else if (play.equalsIgnoreCase("no")) {
                            //menu();
                        }
                    }
                }
                printCards();
                while(!play.equalsIgnoreCase("hit")&&!play.equalsIgnoreCase("stay")) {
                    System.out.print("Hit or Stay: ");
                    play = strScanner.nextLine();
                }
                if(play.equalsIgnoreCase("hit")){
                    hit();
                    play="";
                }
                else if(play.equalsIgnoreCase("stay")){
                    stay=true;
                }
                if(playerTotals[0]>21&&playerTotals[1]>21){
                    busted=true;
                }
                else if(playerTotals[0]==21||playerTotals[1]==21){
                    twentyOne=true;
                }
            }
            if(busted){
                while(!play.equalsIgnoreCase("yes")&&!play.equalsIgnoreCase("no")) {
                    System.out.print("You busted. ");
                    dealerHit();
                    printCards();
                    System.out.print("Try again! Play again? (Yes/No): ");
                    play = strScanner.nextLine();
                    if (play.equalsIgnoreCase("yes")) {
                        new Blackjack();
                    } else if (play.equalsIgnoreCase("no")) {
                        //menu();
                    }
                }
            }
            if(stay){
                dealerHit();
                printCards();
                while(dealerTotals[0]<17||dealerTotals[1]<17) {
                    if (((dealerCards.get(0).equals("A")||dealerCards.get(1).equals("A"))&&((cardsValues.get(playerCards.get(0))+cardsValues.get(playerCards.get(1))+10)<17))||((!dealerCards.get(0).equals("A")&&!dealerCards.get(1).equals("A"))&&(dealerTotals[0]<17||dealerTotals[1]<17))) {
                        dealerHit();
                        printCards();
                    }

                }
                int dealerHighest = 0;
                int playerHighest = 0;
                for (int i = 0; i < 2; i++) {
                    if (dealerTotals[i] <= 21 && dealerTotals[i] > dealerHighest) {
                        dealerHighest = dealerTotals[i];
                    }
                    if (playerTotals[i] <= 21 && playerTotals[i] > playerHighest) {
                        playerHighest = playerTotals[i];
                    }
                }

                if (playerHighest > dealerHighest) {
                    while (!play.equalsIgnoreCase("yes") && !play.equalsIgnoreCase("no")) {
                        System.out.print("\nCongratulations! You win! Play again? (Yes/No): ");
                        play = strScanner.nextLine();
                        if (play.equalsIgnoreCase("yes")) {
                            System.out.println();
                            new Blackjack();
                        } else if (play.equalsIgnoreCase("no")) {
                            //menu();
                        }
                    }
                } else if (playerHighest < dealerHighest) {
                    while (!play.equalsIgnoreCase("yes") && !play.equalsIgnoreCase("no")) {
                        System.out.print("\nDealer wins. Play again? (Yes/No): ");
                        play = strScanner.nextLine();
                        if (play.equalsIgnoreCase("yes")) {
                            System.out.println();
                            new Blackjack();
                        } else if (play.equalsIgnoreCase("no")) {
                            //menu();
                        }
                    }
                }
                else if(playerHighest==dealerHighest) {
                    while (!play.equalsIgnoreCase("yes") && !play.equalsIgnoreCase("no")) {
                        System.out.print("\nYou tied with the dealer! Play again? (Yes/No): ");
                        play = strScanner.nextLine();
                        if (play.equalsIgnoreCase("yes")) {
                            System.out.println();
                            new Blackjack();
                        } else if (play.equalsIgnoreCase("no")) {
                            //menu();
                        }
                    }
                }
            }
        }
        else if(play.equalsIgnoreCase("back")){
            //menu();
        }
        else{
            run();
        }
    }
    public String hit(){
        Random rand = new Random();
        int rInt = rand.nextInt(cardsArray.size());
        String cardPicked="";
        if(!cardsArray.get(rInt).equals("A")){
            playerTotals[0]+=cardsValues.get(cardsArray.get(rInt));
            playerTotals[1]+=cardsValues.get(cardsArray.get(rInt));
            playerCards.add(cardsArray.get(rInt));
            cardPicked=cardsArray.remove(rInt);
        }
        else{
            if(cardsArray.contains("A")){
                playerTotals[0]+=1;
                playerTotals[1]+=11;
                playerCards.add(cardsArray.get(rInt));
                cardsArray.remove("A");
            }
            else{
                hit();
            }
        }
        return "["+cardPicked+"]";
    }
    public String dealerHit(){
        Random rand = new Random();
        int rInt = rand.nextInt(cardsArray.size());
        String cardPicked="";
        if(!cardsArray.get(rInt).equals("A")){
            dealerTotals[0]+=cardsValues.get(cardsArray.get(rInt));
            dealerTotals[1]+=cardsValues.get(cardsArray.get(rInt));
            dealerCards.add(cardsArray.get(rInt));
            cardPicked=cardsArray.remove(rInt);
        }
        else{
            if(cardsArray.contains("A")){
                dealerTotals[0]+=1;
                dealerTotals[1]+=11;
                dealerCards.add(cardsArray.get(rInt));
                cardsArray.remove("A");
            }
            else{
                dealerHit();
            }
        }
        return "["+cardPicked+"]";
    }
    public void printCards(){
        System.out.print("Dealer: ");
        for (String s : dealerCards) {
            System.out.print("[" + s + "]");
        }
        if (dealerTotals[0] != dealerTotals[1]) {
            if (dealerTotals[0] <= 21 && dealerTotals[1] <= 21) {
                System.out.print(" (" + dealerTotals[0] + "," + dealerTotals[1] + ")");
            }
            if (dealerTotals[0] <= 21 && dealerTotals[1] > 21) {
                System.out.print(" (" + dealerTotals[0] + ")");
            }
            if (dealerTotals[0] > 21 && dealerTotals[1] <= 21) {
                System.out.print(" (" + dealerTotals[1] + ")");
            }
        } else {
            System.out.print(" (" + dealerTotals[0] + ")");
        }
        System.out.print(", Player: ");
        for (String s : playerCards) {
            System.out.print("[" + s + "]");
        }
        if (playerTotals[0] != playerTotals[1]) {
            if (playerTotals[0] <= 21 && playerTotals[1] <= 21) {
                System.out.print(" (" + playerTotals[0] + "," + playerTotals[1] + ")");
            }
            if (playerTotals[0] <= 21 && playerTotals[1] > 21) {
                System.out.print(" (" + playerTotals[0] + ")");
            }
            if (playerTotals[0] > 21 && playerTotals[1] <= 21) {
                System.out.print(" (" + playerTotals[1] + ")");
            }
        } else {
            System.out.print(" (" + playerTotals[0] + ")");
        }
        System.out.println();
    }
}
