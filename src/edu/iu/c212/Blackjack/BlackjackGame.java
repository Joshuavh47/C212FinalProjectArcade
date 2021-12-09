package edu.iu.c212.Blackjack;

import java.util.Scanner;

public class BlackjackGame {
    private BlackjackDealer dealer;
    private BlackjackPlayer player;
    public BlackjackGame(){
        dealer=new BlackjackDealer();
        player=new BlackjackPlayer();
        run();
    }
    public void run(){
        System.out.println("Welcome to Blackjack! $20/game $50/win.");

        System.out.print("Type \"play\" to play, or \"back\" to go back: ");
        Scanner scan = new Scanner(System.in);
        String play = scan.nextLine();
        if(play.equalsIgnoreCase("back")){
            //menu();
        }
        while(!play.equalsIgnoreCase("play")&&!play.equalsIgnoreCase("back")){
            System.out.print("Type \"play\" to play, or \"back\" to go back: ");
            play = scan.nextLine();
        }
        System.out.println(dealer.getPartialHand()+" "+dealer.getBestTotal()+" "+player.getCurrentTotalsString()+" "+player.getCurrentTotalsString());
    }
}
