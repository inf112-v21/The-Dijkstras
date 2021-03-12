package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Player {

    private int life;
    private Location robotSpawnPoint;
    private Robot myRobot;
    private int nextFlagIndex = 1;
    private boolean powerDown= false;
    private List<Card> hand = new ArrayList<>();
    private HashMap<Integer, Card> currentCards = new HashMap<>();

    public Player(Location startPosition){
        this.life = 3;
        this.robotSpawnPoint = startPosition;
    }
    public void setRobot(Robot myRobot){ this.myRobot= myRobot; }

    public Robot getRobot(){ return myRobot; }

    public int getLife() {
        return this.life;
    }

    public int getNextFlagIndex(){
        return nextFlagIndex;
    }

    public void robotOnFlagEvent(Flag flag){
        if (flag.getIndex().equals(nextFlagIndex)){
            this.nextFlagIndex++;
        }
    }

    public void decreaseLife(){
        this.life -= 1;
    }

    public Location getSpawnPoint() {
        return this.robotSpawnPoint;
    }

    public void newCheckPoint(Location location) {
        this.robotSpawnPoint = location;}

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
        return 9-myRobot.getHealth();
    }

    public void setHand(HashSet<Card> cards){
        hand.addAll(cards);
    }

    public List<Card> getHand() { return hand; }

    public int cardChoiceAmount() {
        int health = getRobot().getHealth();
        return Math.min(5, health);
    }

    public HashMap<Integer, Card> getCurrentCards() { return currentCards;}

    public void addCurrentCards(Card card, int place) {
        currentCards.put(place,card);
    }

    /**
     * This method should check two conditions, the number of current chosen cards
     * and if the player still has time to choose
     * @return true if player has time and place in currentCards to choose, false otherwise
     */
    public boolean allowedToChooseCards() {
        // Should check if player still has time to choose
        // Should check if getCurrentCards().size() < 5
        // in case there is some locked cards, the currentCards is not empty
        //at the start of this round and this statement " getCurrentCards().size() < cardChoiceAmount()"
        // will not do well in this case.
        return getCurrentCards().size() < cardChoiceAmount();
    }

    public void makeMove(Card movingCard) {
        switch (movingCard.type){
            case MOVE1:    myRobot.moveForward();
                           break;
            case MOVE2:    myRobot.moveForward();
                           myRobot.moveForward();
                           break;
            case MOVE3:    myRobot.moveForward();
                           myRobot.moveForward();
                           myRobot.moveForward();
                           break;
            case BACKUP:   myRobot.reverse();
                           break;
            case ROTLEFT:  myRobot.rotate(-1);
                           break;
            case ROTRIGHT: myRobot.rotate(+1);
                           break;
            case UTURN:    myRobot.rotate(2);
                           break;
            default: throw new IllegalArgumentException(movingCard+" is not a valide card");

        }

    }
}
