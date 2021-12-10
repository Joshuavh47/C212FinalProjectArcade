package edu.iu.c212.places.games.Blackjack;

import java.util.ArrayList;

public class BlackjackDealer extends BlackjackParticipant {
    private boolean bust=false;
    private String shownCard;
    private int dealerBest;
    private boolean stay=false;
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

        return best;
    }
    public String getPartialHand(){
        String temp="";
        if(cardsStringArr().size()==2&&!stay){
            return "Dealer: ["+shownCard+"][X]";
        }
        else if(cardsStringArr().size()==2&&stay){
            return "Dealer: ["+cardsStringArr().get(0)+"]["+cardsStringArr().get(1)+"]";
        }
        else{
            for(String s:cardsStringArr()){
                temp+="["+s+"]";
            }
        }
        return "Dealer: "+temp;
    }
    public void play(){
        while(getBestTotal()<17&&getBestTotal()!=-1){
            hit();
            if(getBestTotal()!=-1) {
                System.out.println(getPartialHand() + " " + getBestTotal());
            }
            else{
                System.out.println(getPartialHand() + " Bust");
            }
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
        else{
            if(cardsStringArr().get(0).equals("A")){
                return "(1,11)";
            }
            else{
                return "("+cardValuesMap().get(cardsStringArr().get(0));
            }
        }

    }
    public ArrayList<String> getCards(){
        return cardsStringArr();
    }
    public String printCards(){
        String temp="Dealer: ";
        for(String s:cardsStringArr()){
            temp+="["+s+"]";
        }
        return temp;
    }

}
