package edu.iu.c212.places.games.Blackjack;

import java.util.ArrayList;

public class BlackjackPlayer extends BlackjackParticipant {
    public BlackjackPlayer(){
        ArrayList<String> deck=cards();
        int[] handTotals=handTotals();
        hit();
        hit();
    }
    public String printCards(){
        String temp="Player: ";
        for(String s:cardsStringArr()){
            temp+="["+s+"]";
        }
        return temp;
    }
    public String getCurrentTotalsString(){
        if(handTotals()[0]!=handTotals()[1]){
            if(handTotals()[0]<21&&handTotals()[1]<21){
                return "("+handTotals()[0]+","+handTotals()[1]+")";
            }
            else if(handTotals()[0]>21&&handTotals()[1]<21){
                return "("+handTotals()[1]+")";
            }
            else if(handTotals()[0]<21&&handTotals()[1]>21){
                return "("+handTotals()[0]+")";
            }
            else if(handTotals()[0]>21&&handTotals()[1]>21){
                return "Busted";
            }
            else if(handTotals()[0]==21||handTotals()[1]==21){
                return "(21)";
            }
        }
        else{
            if(handTotals()[0]<=21){
                return "("+handTotals()[0]+")";
            }
            else{
                return "Busted";
            }
        }
        return null;
    }
    @Override
    public int getBestTotal() {
        int best=-1;
        for(int i=0;i<2;i++){
            if(handTotals()[i]<=21&&handTotals()[i]>best){
                best=handTotals()[i];
            }
        }
        return best;
    }
}
