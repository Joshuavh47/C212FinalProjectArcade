package edu.iu.c212.places.games;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

import java.io.IOException;

public class TriviaGame extends Game{

    public TriviaGame(String name, Arcade playTime, double cost) {
        super(name, playTime, cost);
    }

    @Override
    public void onEnter(User user) throws IOException {

    }
}
