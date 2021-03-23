package inf112.skeleton.Game;

import inf112.skeleton.grid.GameBoard;

import java.util.*;

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
     * This method receive an input card from a player throw GUI
     * @param player
     * @return card
     */
    public Card getInputCardFromPlayer(Player player){
        //TODO find a way to get input card from the player
        Random r = new Random();
        int bound= player.getHand().size();
        return player.getHand().get(r.nextInt(bound));// this line should change and replace with a input card
    }

    /**
     * Allow a player to choose programing cards
     * @param player
     */
    public void chooseCardsManage(Player player)  { //Må kalles før spiller velger noen kort for den runden
        //player must have hand here
        if (player.getHand().isEmpty()) { throw new NoSuchElementException("The player has no cards in their hand");}


        while(player.allowedToChooseCards()){
            Card card = getInputCardFromPlayer(player);
            addChosenCard(player,card);
        }
        // This condition is always false now until the
        // allowedToChooseCards() method takes time into account
        if (player.getCurrentCards().size()<5){
            chooseRandomCard(player);
        }
    }

    private void addChosenCard(Player player, Card card){
        if (!player.allowedToChooseCards()) {return;}

        int place = player.getCurrentCards().size() + 1;
        player.addCurrentCards(card, place);


    }

    /**This method should be called when a player har no more time
     * to choose  programming card from his hand
     * @param player the current player
     * @return card: return random card from the player hand
     */
    public Card chooseRandomCard(Player player){
        List<Card> hand= player.getHand();
        Collections.shuffle(hand);
        return hand.remove(0);

    }

    /**
     * Perform  actions in 5 phases according to programing cards
     * @param players set of all players
     * @param gb gameBoard instance
     */
    public void performMovements(HashSet<Player> players,GameBoard gb){
        int phase=1;
        while(phase<= 5){
            performOneCardMovement(phase,players,gb);
            phase++;
        }

    }

    private void performOneCardMovement(int phase, HashSet<Player> players, GameBoard gb) {
        PriorityQueue<Player> prioritetPlayers =new PriorityQueue<>((p1,p2)->p2.getCurrentCards().get(phase).priorityNr-p1.getCurrentCards().get(phase).priorityNr);
        prioritetPlayers.addAll(players);
        while(!prioritetPlayers.isEmpty()){
            Player p= prioritetPlayers.poll();
            Card nextCard= p.getCurrentCards().remove(phase);
            p.makeMove(nextCard,gb );
           // touchCheckpoints(List<Flag> flags)

        }


    }



}
