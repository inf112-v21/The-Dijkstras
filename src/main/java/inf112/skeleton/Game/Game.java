package inf112.skeleton.Game;

import inf112.skeleton.grid.*;
import java.util.List;

public class Game {
    private boolean gameActive;
    private RoundHandler rh;

    public Game(boolean gameActive, RoundHandler rh) {
        this.gameActive = gameActive;
        this.rh = rh;
    }

    public void startGame() {

        while (gameActive) {
            //Deal programCards
            //Let players register cards
            //Let players choose whether to continuew running or pwoer down
            completeRegisters();
            //Clean up end-of-turn effects
            boolean gameOver = true;
            if (gameOver) {
                this.gameActive = false;
            }
        }
        return;
    }

    public void completeRegisters() {
        //Reveal program cards
        //Robots move
        //Board elements move
        //Lasers fire

    }
}