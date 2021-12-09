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
        if(dealer.cardsStringArr().get(0).equals("A")) {
            System.out.println(dealer.getPartialHand() + " (1,11)    " + player.printCards() + " " + player.getCurrentTotalsString());
        }
        else{
            System.out.println(dealer.getPartialHand() + " (" + dealer.cardValuesMap().get(dealer.getCards().get(0)) + ")    " + player.printCards() + " " + player.getCurrentTotalsString());
        }
        while(!play.equalsIgnoreCase("hit")&&!play.equalsIgnoreCase("stay")) {
            System.out.print("Hit or stay: ");
            play=scan.nextLine();
            System.out.println();
        }
        if(play.equalsIgnoreCase("hit")){
            player.hit();
            play="";
        }
        else if(play.equalsIgnoreCase("stay")){
            dealer.play();

        }

        while(!stay&&!bust&&!bj){
            System.out.println(dealer.printCards()+" "+dealer.getBestTotal()+"    "+player.printCards()+" "+player.getCurrentTotalsString());
            if(player.getCurrentTotalsString().equals("(21)")){
                bj=true;
            }
            if(player.getCurrentTotalsString().equals("Busted")){
                bust=true;
            }
            while(!play.equalsIgnoreCase("hit")&&!play.equalsIgnoreCase("stay")&&!bust) {
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
            }

        }
        if(bj){
            System.out.println("Blackjack! You win!");
            while(!play.equalsIgnoreCase("yes")&&!play.equalsIgnoreCase("no")) {
                System.out.print("Play again? (Yes/No): ");
                play=scan.nextLine();
                System.out.println();
            }
            if(play.equalsIgnoreCase("yes")){
                new BlackjackGame();
            }
            else if(play.equalsIgnoreCase("no")){
                //menu();
            }
        }
        if(bust){

            System.out.println("You lose!");
            while(!play.equalsIgnoreCase("yes")&&!play.equalsIgnoreCase("no")) {
                System.out.print("Play again? (Yes/No): ");
                play=scan.nextLine();
                System.out.println();
            }
            if(play.equalsIgnoreCase("yes")){
                new BlackjackGame();
            }
            else if(play.equalsIgnoreCase("no")){
                //menu();
            }
        }
        if(stay){
            dealer.play();
            if(dealer.busted()){
                System.out.println("You win, dealer went bust!");
                while(!play.equalsIgnoreCase("yes")&&!play.equalsIgnoreCase("no")){
                    System.out.print("Play again? (Yes/No): ");
                    play=scan.nextLine();
                    System.out.println();
                }
                if(play.equalsIgnoreCase("yes")){
                    new BlackjackGame();
                }
                else if(play.equalsIgnoreCase("no")){
                    //menu();
                }
            }
            else{
                if(dealer.getBestTotal()>player.getBestTotal()){
                    System.out.println("You lose!");
                    while(!play.equalsIgnoreCase("yes")&&!play.equalsIgnoreCase("no")) {
                        System.out.print("Play again? (Yes/No): ");
                        play=scan.nextLine();
                        System.out.println();
                    }
                    if(play.equalsIgnoreCase("yes")){
                        new BlackjackGame();
                    }
                    else if(play.equalsIgnoreCase("no")){
                        //menu();
                    }
                }
                else if(dealer.getBestTotal()<player.getBestTotal()){
                    System.out.println("You win!");
                    while(!play.equalsIgnoreCase("yes")&&!play.equalsIgnoreCase("no")) {
                        System.out.print("Play again? (Yes/No): ");
                        play=scan.nextLine();
                        System.out.println();
                    }
                    if(play.equalsIgnoreCase("yes")){
                        new BlackjackGame();
                    }
                    else if(play.equalsIgnoreCase("no")){
                        //menu();
                    }
                }
                else{
                    System.out.println("You tied with the dealer!");
                    while(!play.equalsIgnoreCase("yes")&&!play.equalsIgnoreCase("no")) {
                        System.out.print("Play again? (Yes/No): ");
                        play=scan.nextLine();
                        System.out.println();
                    }
                    if(play.equalsIgnoreCase("yes")){
                        new BlackjackGame();
                    }
                    else if(play.equalsIgnoreCase("no")){
                        //menu();
                    }
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
