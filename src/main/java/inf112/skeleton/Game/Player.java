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
    private boolean powerDown = false;
    private List<Card> hand = new ArrayList<>();
    private HashMap<Integer, Card> chosenCards = new HashMap<>();
    private List<Card> restCards = new ArrayList<>();

    public Player(Location startPosition) {
        this.life = 3;
        this.robotSpawnPoint = startPosition;
        this.myRobot = new Robot();
    }

    // Get && set robot
    public void setRobot(Robot myRobot) {
        this.myRobot = myRobot;
    }

    public Robot getRobot() {
        return myRobot;
    }


    public void placeRobotAtSpawn(GameBoard gb) {
        if (gb.contains(myRobot)) {
            Location loc = gb.locationOf(myRobot);
            gb.clearLocation(loc);
        }
        gb.set(robotSpawnPoint, myRobot);
    }


    public Location getSpawnPoint() {
        return this.robotSpawnPoint;
    }

    public void newCheckPoint(Location location) {
        this.robotSpawnPoint = location;
    }


    public int getLife() {
        return this.life;
    }

    public void decreaseLife() {
        this.life -= 1;
    }

    public int getNumberOfDamages() {
        return 9 - myRobot.getHealth();
    }


    public int getNextFlagIndex() {
        return nextFlagIndex;
    }

    /**
     * Checks if flag has correct index.
     */
    public void checkFlagIndex(Flag flag) {
        if (flag.getIndex() == nextFlagIndex) {
            this.nextFlagIndex++;
        }
    }


    public boolean isPowerDown() {
        return powerDown;
    }

    public void announcePowerDown() {
        powerDown = true;
    }

    public void cancelPowerDown() {
        powerDown = false;
    }


    /**
     * Gives player cards to choose from.
     */
    public void setHand(HashSet<Card> cards) {
        hand.addAll(cards);
    }

    public List<Card> getHand() {
        return hand;
    }

    public HashMap<Integer, Card> getChosenCards() {
        return chosenCards;
    }

    public void addChosenCard(Card card, int place) {
        chosenCards.put(place, card);
    }

    /**
     * Checks how many cards the player should get given current life.
     */
    public int cardChoiceAmount() {
        int health = getRobot().getHealth();
        return Math.min(5, health);
    }

    /**
     * This method should check two conditions, the number of current chosen cards
     * and if the player still has time to choose
     *
     * @return true if player has time and place in chosenCards to choose, false otherwise
     */
    public boolean allowedToChooseCards() {
        // Should check if player still has time to choose
        // Should check if getChosenCards().size() < 5
        // in case there is some locked cards, the chosenCards is not empty
        //at the start of this round and this statement " getChosenCards().size() < cardChoiceAmount()"
        // will not do well in this case.
        return getChosenCards().size() < cardChoiceAmount();
    }


    /**
     * Move the robot out from a card, moving occurs on the GameBoard if the card is a moving card,
     * Otherwise change the robot direction
     *
     * @param movingCard the programming card
     * @param gb         GameBoard object where the moving is occurring
     */
    public void makeMove(Card movingCard, GameBoard gb) {
        Directions currentDir = myRobot.getDirection();
        switch (movingCard.type) {
            case MOVE1:
                gb.moveRobot(currentDir, myRobot);
                break;
            case MOVE2:
                gb.moveRobot(currentDir, myRobot);
                gb.moveRobot(currentDir, myRobot);
                break;
            case MOVE3:
                gb.moveRobot(currentDir, myRobot);
                gb.moveRobot(currentDir, myRobot);
                gb.moveRobot(currentDir, myRobot);
                break;
            case BACKUP:
                gb.moveRobot(currentDir.rotate(2), myRobot);
                break;
            case ROTLEFT:
                myRobot.rotate(-1);
                break;
            case ROTRIGHT:
                myRobot.rotate(+1);
                break;
            case UTURN:
                myRobot.rotate(2);
                break;
            default:
                throw new IllegalArgumentException(movingCard + " is not a valid card");

        }

    }


    public void updateCurrentCards() {
        if (myRobot.getHealth() >= 5) {
            restCards.addAll(chosenCards.values());
            chosenCards.clear();
        } else {

            int freeCards = myRobot.getHealth();
            while (freeCards > 0) {
                restCards.add(currentCards.remove(freeCards));
                freeCards--;

            }
        }
    }

    public List<Card> getRestCards() {
        return restCards;
    }
}
