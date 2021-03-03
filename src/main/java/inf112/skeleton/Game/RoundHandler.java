package inf112.skeleton.Game;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

    private boolean chooseCard(Player player, Card card){
        //player must have hand here
        //if (player.getHand().isEmpty()) { throw new Exception("The player has no cards in their hand");}
        if (!player.allowedToChooseCards()) {return false;}

        int place = player.getCurrentCards().size() + 1;
        player.addCurrentCards(card, place);

        return true;
    }

    private void chooseRandomCard(Player player){
        List<Card> hand= player.getHand();
        Collections.shuffle(hand);
        chooseCard(player,hand.remove(0));

    }

    public void chooseCardsManage(Player player){ //Må kalles før spiller velger noen kort for den runden
        int allowed = player.cardChoiceAmount();

        while(allowed>0){
            allowed--;
            //chooseCard(player,)
        }
        if (player.getCurrentCards().size()<5){
            chooseRandomCard(player);
        }

    }
}
