package edu.iu.c212.places.games.Blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class BlackjackParticipant {

    int[] totalValue = new int[2];
    ArrayList<String> cardsArrStr=new ArrayList<String>();
    protected ArrayList<String> cards(){
        ArrayList<String> cardsArray = new ArrayList<String>();
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
        return cardsArray;

    }
    protected HashMap<String, Integer> cardValuesMap(){
        HashMap<String, Integer> cardsValues = new HashMap<String, Integer>();
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
        return cardsValues;
    }
    protected int[] handTotals(String card){

        if(!card.equals("A")){
            totalValue[0]+=cardValuesMap().get(card);
            totalValue[1]+=cardValuesMap().get(card);
        }
        else{
            totalValue[0]+=1;
            totalValue[1]+=11;
        }
        return totalValue;
    }
    protected int[] handTotals(){
        return totalValue;
    }
    public ArrayList<String> cardsStringArr(String s){
        cardsArrStr.add(s);
        return cardsArrStr;
    }
    public ArrayList<String> cardsStringArr(){
        return cardsArrStr;
    }
    public void hit(){
        Random rand = new Random();
        int rInt = rand.nextInt(cards().size());
        if(!cards().get(rInt).equals("A")){
            handTotals(cards().get(rInt));
            cardsStringArr(cards().get(rInt));
            cards().remove(rInt);
        }
        else{
            if(cards().contains("A")){
                handTotals("A");
                cardsStringArr(cards().get(rInt));
                cards().remove("A");
            }
            else{
                hit();
            }
        }

    }
    public abstract int getBestTotal();
}
