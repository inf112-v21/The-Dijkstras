package inf112.skeleton.Game;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

public class RoundHandler {
    public Deck deck;

    public RoundHandler(){
        deck= new Deck();
    }

    public int DetermineTheNumberOfCards(Player player) {
        if ( player.isPowerDown()) {
            return 0;
        }
        return player.getRobot().getHealth();
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

    /**
     * This method receives an input card from a player through GUI
     */
    public Card getInputCardFromPlayer(Player player){
        //TODO find a way to get input card from the player
        return player.getHand().get(0);// this line should change and replace with a input card
    }

    /**
     *  Manages the players selection of cards
     */
    public void chooseCardsManager(Player player)  { //Må kalles før spiller velger noen kort for den runden
        //player must have hand here
        if (player.getHand().isEmpty()) { throw new NoSuchElementException("The player has no cards in their hand");}


        while(player.allowedToChooseCards()){
            Card card = getInputCardFromPlayer(player);
            addChosenCard(player,card);
        }
        // This condition is always false until the
        // allowedToChooseCards() method takes time into account
        if (player.getChosenCards().size()<5){
            chooseRandomCard(player);
        }
    }

    private void addChosenCard(Player player, Card card){
        if (!player.allowedToChooseCards()) {return;}

        int place = player.getChosenCards().size() + 1;
        player.addChosenCard(card, place);


    }

    /**
     * This method should be called when a player has no more time
     * to choose programming cards
     */
    public Card chooseRandomCard(Player player){
        List<Card> hand= player.getHand();
        Collections.shuffle(hand);
        return hand.remove(0);

    }

public void performMovements(){

}

}
