package inf112.skeleton.Game;

import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.GameBoard;
import inf112.skeleton.grid.Location;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Player implements ITileObject {

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
        this.myRobot = new Robot();
    }

    // Get && set robot
    public void setRobot(Robot myRobot){ this.myRobot= myRobot; }

    public Robot getRobot(){ return myRobot; }


    // kanskje denne metoden børe flyttes til GameBoard klasse fordi GameBoard styrer med posisjoner
    public void placeRobotAtSpawn(GameBoard gb){
        gb.set(robotSpawnPoint, myRobot);
    }

    // checkPoint
    public Location getSpawnPoint() {
        return this.robotSpawnPoint;
    }

    public void newCheckPoint(Location location) {
        this.robotSpawnPoint = location;}


//life and damages
    public int getLife() {
        return this.life;
    }

    public void decreaseLife(){
        this.life -= 1;
    }

    public int getNumberOfDamages(){
        return 9-myRobot.getHealth();
    }


//flag
    public int getNextFlagIndex(){
        return nextFlagIndex;
    }

    public void checkFlagIndex(Flag flag){
        if (flag.getIndex() == nextFlagIndex){
            this.nextFlagIndex++;
        }
    }


//power down

    public boolean isPowerDown() {
        return powerDown;
    }

    public void announcePowerDown(){
        powerDown= true;
    }

    public void cancelPowerDown(){
        powerDown= false;
    }


// cards on hand
    public void setHand(HashSet<Card> cards){
        hand.addAll(cards);
    }

    public List<Card> getHand() { return hand; }

    // selected cards
    public HashMap<Integer, Card> getCurrentCards() { return currentCards;}

    public void addCurrentCards(Card card, int place) {
        currentCards.put(place,card);
    }

    // Ability to Choose
    public int cardChoiceAmount() {
        int health = getRobot().getHealth();
        return Math.min(5, health);
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


    /**
     * Move the robot out from a card, moving occurs on the GameBoard if the card is a moving card,
     * Otherwise change the robot direction
     * @param movingCard the programming card
     * @param gb GameBoard object where the moving is occurring
     */
    public void makeMove(Card movingCard, GameBoard gb) {
        Directions currentDir= myRobot.getDirection();
        switch (movingCard.type){
            case MOVE1:
                gb.moveRobot(currentDir,myRobot);
                break;
            case MOVE2:
                gb.moveRobot(currentDir,myRobot);
                gb.moveRobot(currentDir,myRobot);
                break;
            case MOVE3:
                gb.moveRobot(currentDir,myRobot);
                gb.moveRobot(currentDir,myRobot);
                gb.moveRobot(currentDir,myRobot);
                break;
            case BACKUP:   gb.moveRobot(currentDir.rotate(2),myRobot);
                break;
            case ROTLEFT:  myRobot.rotate(-1);
                break;
            case ROTRIGHT: myRobot.rotate(+1);
                break;
            case UTURN:    myRobot.rotate(2);
                break;
            default: throw new IllegalArgumentException(movingCard+" is not a valid card");

        }

    }
}
