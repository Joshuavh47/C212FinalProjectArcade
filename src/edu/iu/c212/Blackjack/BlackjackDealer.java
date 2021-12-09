package edu.iu.c212.Blackjack;

import java.util.ArrayList;

public class BlackjackDealer extends BlackjackParticipant {
    private boolean bust=false;
    private String shownCard;
    private int dealerBest;
    public BlackjackDealer(){
        ArrayList<String> deck=cards();
        int[] handTotals=handTotals();
        hit();
        hit();
        shownCard=cardsStringArr().get(0);
        dealerBest=getBestTotal();
    }
    @Override
    public int getBestTotal() {
        int best=-1;
        int newBest=0;
        if (cardsStringArr().size()==2){
            if(shownCard.equals("A")){
                best=11;
            }
            else {
                best = cardValuesMap().get(shownCard);
            }
        }
        else{
            int bestAce=0;
            for(String s:cardsStringArr()){
                if(!s.equals("A")){
                    newBest+=cardValuesMap().get(s);
                    bestAce+=cardValuesMap().get(s);
                }
                else{
                    newBest+=cardValuesMap().get(s);
                    bestAce+=11;
                }
            }
            if (bestAce<=21){
                best=bestAce;
            }
            else if(bestAce>21&&newBest<=21){
                best=newBest;
            }
        }
        return best;
    }
    public String getPartialHand(){
        String temp="";
        if(cardsStringArr().size()==2){
            return "Dealer: ["+shownCard+"][X]";
        }
        else{
            for(String s:cardsStringArr()){
                temp+="["+s+"]";
            }
        }
        return "Dealer: "+temp;
    }
    public void play(){
        while(getBestTotal()<17){
            hit();
            System.out.println(getPartialHand());
        }
        if(getBestTotal()==-1){
            System.out.println("Bust");
            bust=true;
        }
    }
    public boolean busted(){
        return bust;
    }
    public String getBestTotalString(){
        if(cardsStringArr().size()!=2) {
            if (handTotals()[0] != handTotals()[1]) {
                if (handTotals()[0] <= 21 && handTotals()[1] <= 21) {
                    return "(" + handTotals()[0] + "," + handTotals()[1] + ")";
                } else if (handTotals()[0] > 21 && handTotals()[1] <= 21) {
                    return "(" + handTotals()[1] + ")";
                } else if (handTotals()[0] <= 21 && handTotals()[1] > 21) {
                    return "(" + handTotals()[0] + ")";
                } else if (handTotals()[0] > 21 && handTotals()[1] > 21) {
                    return "Busted";
                }
            } else {
                if (handTotals()[0] <= 21) {
                    return "(" + handTotals()[0] + ")";
                } else {
                    return "Busted";
                }
            }
            return null;
        }

    }

}
