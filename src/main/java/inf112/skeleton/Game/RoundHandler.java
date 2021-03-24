package inf112.skeleton.Game;

import inf112.skeleton.grid.GameBoard;

import java.util.*;

public class RoundHandler {
    public Deck deck;
    private GameBoard gameboard;
    private final List<Flag> flags;
    private final HashSet<Player> players;


    public RoundHandler(GameBoard gameboard, List<Flag> flags, HashSet<Player> players) {
        this.gameboard = gameboard;
        this.flags = flags;
        this.players = players;
        deck = new Deck();
    }


    public int DetermineTheNumberOfCards(Player player) {
        if (player.isPowerDown()) {
            return 0;
        }
        return player.getRobot().getHealth();
    }

    public void dealProgramCards() {
        Collections.shuffle(deck.cardDeck);

        for (Player player : players) {

            HashSet<Card> hand = new HashSet<>();
            int numOfCards = DetermineTheNumberOfCards(player);

            while (numOfCards > 0) {

                hand.add(deck.cardDeck.remove(0));
                numOfCards--;
            }

            player.setHand(hand);

        }
    }

    /**
     * This method receives an input card from a player through GUI
     */
    public Card getInputCardFromPlayer(Player player) {
        //TODO find a way to get input card from the player
        Random r = new Random();
        int bound = player.getHand().size();
        return player.getHand().get(r.nextInt(bound));// this line should change and replace with a input card
    }

    /**
     *  Manages the players selection of cards
     */
    public void chooseCardsManage(Player player)  { //Må kalles før spiller velger noen kort for den runden
        //player must have hand here
        if (player.getHand().isEmpty()) {
            throw new NoSuchElementException("The player has no cards in their hand");
        }


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

    private void addChosenCard(Player player, Card card) {
        if (!player.allowedToChooseCards()) {
            return;
        }

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

    /**
     * Perform  actions in 5 phases according to programing cards
     */
    public void performMovements() {
        int phase = 1;
        while (phase <= 5) {
            performOneCardMovement(phase);
            phase++;
        }
        cleanUP();

    }

    private void performOneCardMovement(int phase) {
        PriorityQueue<Player> prioritetPlayers = new PriorityQueue<>((p1, p2) -> p2.getCurrentCards().get(phase).priorityNr - p1.getCurrentCards().get(phase).priorityNr);
        prioritetPlayers.addAll(players);
        while (!prioritetPlayers.isEmpty()) {
            Player p = prioritetPlayers.poll();
            Card nextCard = p.getChosenCards().get(phase);
            p.makeMove(nextCard, gameboard);
            touchCheckpoints();
        }
    }

    /**
     * Check if robot has touched flag or repair sites
     */
    public void touchCheckpoints() {
        checkWrenchSpace();
        flagCheck();
    }

    private void checkWrenchSpace() {
    }

    private void flagCheck() {
        for (Player player : players) {
            for (Flag flag : flags) {
                if (gameboard.sameXYLocation(player.getRobot(), flag)) {
                    player.checkFlagIndex(flag);
                }
            }
        }

    }

    /**
     * Repairs robot on wrenchSpace and update the current cards for each player
     */
    public void cleanUP() {
        updateRobotsSpawnPoint();
        cleanOrLockeCards();

    }

    private void updateRobotsSpawnPoint() {
    }

    private void cleanOrLockeCards() {
        for (Player p : players) {
            p.updateCurrentCards();
        }
    }


    public void collectCards() {
        for (Player p : players) {
            deck.addRestCards(p.getHand());
            deck.addRestCards(p.getRestCards());
        }

}

}
