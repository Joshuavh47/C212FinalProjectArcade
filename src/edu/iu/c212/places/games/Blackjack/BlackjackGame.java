package edu.iu.c212.places.games.Blackjack;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

import java.io.IOException;
import java.util.Scanner;

public class BlackjackGame extends edu.iu.c212.places.games.Game {
    private BlackjackDealer dealer;
    private BlackjackPlayer player;
    private boolean stay=false;
    private boolean bust=false;
    private boolean bj=false;
    private User u=new User();
    public BlackjackGame(String name, Arcade playTime, double cost){
        super(name, playTime, cost);
        dealer=new BlackjackDealer();
        player=new BlackjackPlayer();

    }
    @Override
    public void onEnter(User user) throws IOException {
        u=user;
        run();

    }

    public void run(){
        System.out.println("Welcome to Blackjack! $20/game $50/win $70/blackjack.");

        Scanner scan = new Scanner(System.in);
        String play="";

        if(dealer.cardsStringArr().get(0).equals("A")) {
            System.out.println(dealer.getPartialHand() + " (1,11)    " + player.printCards() + " " + player.getCurrentTotalsString());
        }
        else{
            System.out.println(dealer.getPartialHand() + " (" + dealer.cardValuesMap().get(dealer.getCards().get(0)) + ")    " + player.printCards() + " " + player.getCurrentTotalsString());
        }
        if(player.getCurrentTotalsString().equals("(21)")&&player.cardsStringArr().size()==2){
            bj=true;
        }
        while(!play.equalsIgnoreCase("hit")&&!play.equalsIgnoreCase("stay")&&!bj) {
            System.out.print("Hit or stay: ");
            play=scan.nextLine();
            System.out.println();
        }
        if(play.equalsIgnoreCase("hit")){
            player.hit();
            play="";
        }
        else if(play.equalsIgnoreCase("stay")){
            stay=true;
            dealer.play();

        }

        while(!stay&&!bust&&!bj){
            if(dealer.cardsStringArr().get(0).equals("A")&&!stay&&!bust&&!bj) {
                System.out.println(dealer.getPartialHand() + " (1,11)    " + player.printCards() + " " + player.getCurrentTotalsString());
            }
            else if(!dealer.cardsStringArr().get(0).equals("A")&&!stay&&!bust&&!bj){
                System.out.println(dealer.getPartialHand() + " (" + dealer.cardValuesMap().get(dealer.getCards().get(0)) + ")    " + player.printCards() + " " + player.getCurrentTotalsString());
            }
            if(player.getCurrentTotalsString().equals("(21)")&&player.cardsStringArr().size()==2){
                bj=true;
            }
            if(player.getCurrentTotalsString().equals("Busted")){
                bust=true;
            }
            while(!play.equalsIgnoreCase("hit")&&!play.equalsIgnoreCase("stay")&&!bust&&!bj) {
                System.out.print("Hit or stay: ");
                play=scan.nextLine();
                System.out.println();
            }
            if(play.equalsIgnoreCase("hit")){
                player.hit();
                play="";
            }

            else if(play.equalsIgnoreCase("stay")){
                stay=true;
                dealer.play();
            }

        }
        if(bj){
            System.out.println("Blackjack! You win $70!");
            u.addBalance(90.0);
        }
        if(bust){

            System.out.println("You lose! Try again!");

        }
        if(stay){
            dealer.play();
            if(dealer.busted()){
                System.out.println("You win $50, dealer went bust!");
                u.addBalance(70.0);
            }
            else{
                if(dealer.getBestTotal()>player.getBestTotal()){
                    System.out.println(dealer.printCards()+" "+dealer.getBestTotalString());
                    System.out.println("You lose! Try again!");

                }
                else if(dealer.getBestTotal()<player.getBestTotal()){
                    System.out.println("You win $50!");
                    u.addBalance(70.0);
                }
                else{
                    System.out.println("You tied with the dealer! Your original bet has been returned");
                    u.addBalance(20.0);
                }
            }

        }
        try {
            arcade.getAllPlaces().get(0).onEnter(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
