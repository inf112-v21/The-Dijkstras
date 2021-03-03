package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Player {

    private int life;
    private Location lastCheckPoint;
    private Robot myRobot;

    private boolean powerDown= false;
    private List<Card> hand = new ArrayList<>();
    private HashMap<Integer, Card> currentCards = new HashMap<Integer, Card>();

    public Player(Location startPosition){
        this.life = 3;
        this.lastCheckPoint= startPosition;
    }
    public void setRobot(Robot myRobot){ this.myRobot= myRobot; }

    public Robot getRobot(){ return myRobot; }

    public int getLife() {
        return this.life;
    }

    public void decreaseLife(){
        this.life -= 1;
    }

    public Location getLastCheckPoint() {
        return lastCheckPoint;
    }

    public boolean isPowerDown() {
        return powerDown;
    }

    public void announcePowerDown(){
        powerDown= true;
    }

    public void cancelPowerDown(){
        powerDown= false;
    }

    public int getNumberOfDamages(){
        return 8-myRobot.getHealth();
    }

    public void setHand(HashSet<Card> cards){
        hand.addAll(cards);
    }

    public List<Card> getHand() { return hand; }

    public int cardChoiceAmount() {
        int health = getRobot().getHealth();
        if (health >= 5) return 5;
        return health;
    }

    public boolean allowedToChooseCards() {
        boolean allowed = true;
        if (getCurrentCards().size() >= cardChoiceAmount()) allowed = false;
        return allowed;
    }

    public void addCurrentCards(Card card, int place) {
        currentCards.put(place,card);
    }

    public HashMap<Integer, Card> getCurrentCards() { return currentCards;}
}
