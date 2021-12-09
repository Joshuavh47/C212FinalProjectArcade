package edu.iu.c212.places.games.hangman;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import com.mycompany.c212final.User;
import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.games.Game;
import edu.iu.c212.utils.http.HttpUtils;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author johhe
 */
public class HangmanGame extends Game implements IHangmanGame{

    List<Character> letters;
    String word; //HttpUtils.getRandomHangmanWord;
    User player;
    List<Character> guesses;
    int numWrongGuesses = 0;
    //Arcade arcade;

    public HangmanGame(String name, Arcade playTime, double cost) {
        super(name, playTime, cost);
        this.player = playTime.currentUser;
        //this.arcade = Hangman.class.playtime;
        // add random word generator
        letters = new ArrayList<Character>();
        guesses = new ArrayList<Character>();
        createLetterList();

    }
    @Override
    public void onEnter(User user) throws IOException {

        word = HttpUtils.getRandomHangmanWord();
        //User player = new User("John-Herron",100,null);
        // get hangman game from arcade list of places
        int indexOfGame = arcade.findPlace("Hangman");
        Place game = arcade.allPlaces.get(indexOfGame);
        HangmanGame game1 = new HangmanGame(game.placeName, game.arcade, game.entryFee);
        System.out.println("Welcome to Hangman");
        System.out.println("Rules:");
        System.out.println("The entry fee for the game is $5");
        System.out.println("You will have 6 guesses to guess the word");
        System.out.println("If declared a winner, your prize is $15");
        System.out.println("Lets get started");
        System.out.println("______________________________________________________");
        boolean winner = false;
        while(winner == false){
            System.out.println("You've guesssed " + game1.getNumGuesses() + " times incorrectly " + game1.printGuesses());
            System.out.print("The current word is: " + game1.getBlurredWord());
            System.out.print(". Please enter a lowercase letter in the following lexicon to guess: " + game1.printLetters());
            Scanner in = new Scanner(System.in);
            System.out.println("");
            String guess = in.nextLine();
            if(guess.length()>1 && game1.fullWordCheck(guess) == false){
                System.out.println("Your guess was incorrect. The word was " + game1.getRandomWord());
                break;
            }
            else if(guess.length()>1 && game1.fullWordCheck(guess) == true){
                System.out.println("Congrats, you won with " + game1.getNumGuesses() + " incorrect guesses! You got $15");
                System.out.println("========");
                player.addBalance(15);
                break;
            }
            // check if guess was from the guess list
            game1.validGuess(guess.charAt(0));
            // add guess to list of guesses
            game1.addGuess(guess.charAt(0));
            // update number of incorrect guesses
            game1.checkGuess(guess.charAt(0));
            if(game1.winner()){
                System.out.println("Congrats, you won with " + game1.getNumGuesses() + " incorrect guesses! You got $15");
                System.out.println("========");
                player.addBalance(15);
                winner = true;
                break;
            }
            else if(game1.getNumGuesses()>=6){
                System.out.println("You've ran out of guesses. Thank you for playing.");
                break;
            }
            System.out.println("");
        }

        arcade.allPlaces.get(0).onEnter(player);
    }

    void createLetterList(){
        this.letters.add('a'); this.letters.add('b');
        this.letters.add('c'); this.letters.add('d');
        this.letters.add('e'); this.letters.add('f');
        this.letters.add('g'); this.letters.add('h');
        this.letters.add('i'); this.letters.add('j');
        this.letters.add('k'); this.letters.add('l');
        this.letters.add('m'); this.letters.add('n');
        this.letters.add('o'); this.letters.add('p');
        this.letters.add('q'); this.letters.add('r');
        this.letters.add('s'); this.letters.add('t');
        this.letters.add('u'); this.letters.add('v');
        this.letters.add('w'); this.letters.add('x');
        this.letters.add('y'); this.letters.add('z');

    }

    int getNumGuesses(){
        return this.numWrongGuesses;
    }
    String getRandomWord(){
        return this.word;
    }

    String printGuesses(){
        if(this.guesses.isEmpty()){
            return "([])";
        }
        else{
            return "("+ this.guesses.toString() +")";
        }
    }

    String printLetters(){
        return this.letters.toString();
    }

    void addGuess(char s){
        this.guesses.add(s);
    }

    void checkGuess(char s){
        boolean goodGuess = false;
        for(int i = 0; i <this.word.length();i++){
            if(this.word.charAt(i) == s){
                goodGuess = true;
                break;
            }
        }
        if(goodGuess == false){
            this.numWrongGuesses += 1;
        }
    }

    void validGuess(char e){
        if(this.letters.contains(e) == false){
            System.out.println("Error: incorrect input");
        }
    }

    boolean winner(){
        if(this.numWrongGuesses >6){
            return false;
        }
        else{
            for(int i = 0; i < word.length(); i++){
                if(guesses.contains(word.charAt(i)) == false){
                    return false;
                }
            }
            return true;
        }
    }
    boolean fullWordCheck(String n){
        if(n.equals(this.word)){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public String getBlurredWord() {
        String blurredWord = "";
        for(int i = 0; i < word.length(); i++){
            if(guesses.contains(word.charAt(i))){
                char charLetter = word.charAt(i);
                String strLetter = String.valueOf(charLetter);
                blurredWord += strLetter;
            }
            else{
                blurredWord += "*";
            }
        }
        return blurredWord;
    }


    @Override
    public List<Character> getValidLexicon() {
        return this.letters;
    }

//    public static void main(String[] args) {
//
//        User player = new User("John-Herron",100,null);
//        Hangman game1 = new Hangman(player);
//        // where to allow the user to play the game
//        player.subtractBalance(5);
//
//        System.out.println("Welcome to Hangman");
//        System.out.println("Rules:");
//        System.out.println("The entry fee for the game is $5");
//        System.out.println("You will have 6 guesses to guess the word");
//        System.out.println("If declared a winner, your prize is $15");
//        System.out.println("Lets get started");
//        System.out.println("______________________________________________________");
//
//        boolean winner = false;
//        while(winner == false){
//            System.out.println("You've guesssed " + game1.getNumGuesses() + " times incorrectly " + game1.printGuesses());
//            System.out.print("The current word is: " + game1.getBlurredWord());
//            System.out.print(". Please enter a lowercase letter in the following lexicon to guess: " + game1.printLetters());
//            Scanner in = new Scanner(System.in);
//            System.out.println("");
//            String guess = in.nextLine();
//            if(guess.length()>1 && game1.fullWordCheck(guess) == false){
//                System.out.println("Your guess was incorrect. The word was " + game1.getRandomWord());
//                break;
//            }
//            else if(guess.length()>1 && game1.fullWordCheck(guess) == true){
//                System.out.println("Congrats, you won with " + game1.getNumGuesses() + " incorrect guesses! You got $15");
//                System.out.println("========");
//                player.addBalance(15);
//                break;
//            }
//            game1.validGuess(guess.charAt(0));
//            game1.addGuess(guess.charAt(0));
//            game1.checkGuess(guess.charAt(0));
//            if(game1.winner()){
//                System.out.println("Congrats, you won with " + game1.getNumGuesses() + " incorrect guesses! You got $15");
//                System.out.println("========");
//                player.addBalance(15);
//                winner = true;
//            }
//            else if(game1.getNumGuesses()>=6){
//                System.out.println("You've ran out of guesses. Thank you for playing.");
//                break;
//            }
//            System.out.println("");
//        }
//    }

}


