package inf112.skeleton.Game;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class RoundHandler {
    public Deck deck;

    public RoundHandler(){
        deck= new Deck();
    }



    public int DetermineTheNumberOfCards(Player player) {
        if ( player.isPowerDown()) {
            return 0;
        }
        return 1+player.getRobot().getHealth();
    }

    public void dealProgramCards(HashSet<Player> players) {
        Collections.shuffle(deck.cardDeck);

        for (Player player : players){

            HashSet<Card> hand = new HashSet<>();
            int numOfCards= DetermineTheNumberOfCards(player);

            while(numOfCards>0){

                hand.add(deck.cardDeck.remove(0));
                numOfCards--;
            }

            player.setHand(hand);
        }
    }
}
