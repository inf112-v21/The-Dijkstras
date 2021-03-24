package inf112.skeleton.Game;

import inf112.skeleton.grid.*;

import java.util.*;

//import static sun.swing.MenuItemLayoutHelper.max;

public class Game {
    private boolean gameActive;
    private RoundHandler rh;

    public Game(boolean gameActive, RoundHandler rh) {
        this.gameActive = gameActive;
        this.rh = rh;
    }

    /**
     * All the player in players set should be initialized
     */
    public void randomSetup() {
        if (flags.isEmpty() || players.isEmpty()) {
            throw new NullPointerException("Need players and flags to setup");
        }
        int gbHeight = rh.gameBoard.getRows();
        int gbWidth = rh.gameBoard.getCols();

        int yFlag = gbHeight - 1;

        int layerFlag = 2;

        List<Integer> xCoordinates = new ArrayList<>();
        for (int i = 1; i < gbWidth - 1; i++) {
            xCoordinates.add(i);
        }
        Collections.shuffle(xCoordinates);
        int i = 0;
        for (Flag flag : flags) {
            rh.gameBoard.set(new Location(xCoordinates.get(i), yFlag, layerFlag), flag);
            i++;
        }

        i = 0;
        for (Player player : players) {
            player.setRobot(new Robot("robot_" + i));
            player.placeRobotAtSpawn(rh.gameBoard);
            i++;
        }
    }

    //Q: hva er det minimum man trenger for å kjøre et spill (mvp)
    //A: GameBoard, Robot, Player, Flag,

    public void startGame() {

        while (gameActive) {
            //Deal programCards
            //Let players register cards
            for (Player player : players) {
                if (player.isPowerDown()) continue;
                rh.chooseCardsManager(player);
                //Let players choose whether to continue running or power down

            }
            System.out.println(players);
            rh.performMovements(players);
            rh.touchCheckpoints(flags, players);
            rh.cleanUP(players);
            rh.collectCards(players);
            timeToPowerDown();
            isRobotDead();
            isGameOver();
        }
        return;
    }
    /**
    *temporary method
    */
    private void timeToPowerDown() {
        for (Player p : players) {
            if (p.getRobot().getHealth() < 3) {
                p.announcePowerDown(rh.gameBoard);
            }
        }
    }

    public void isGameOver() {
        for (Player p : players) {
            if (p.getNextFlagIndex() > flags.size()) {
                gameActive = false;
                debugPrint("Game over, winner is: " + p.getRobot());
            }
        }
    }
}