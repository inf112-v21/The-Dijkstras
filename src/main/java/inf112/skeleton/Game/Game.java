package inf112.skeleton.Game;

import inf112.skeleton.grid.*;

import java.util.*;

//import static sun.swing.MenuItemLayoutHelper.max;

public class Game {
    private boolean gameActive;
    private RoundHandler rh;
    protected final List<Flag> flags;
    protected final HashSet<Player> players;
    protected final HashSet<Player> deadPlayers;
    private boolean debugMode = true;
    //  GameBoard gameBoard = new GameBoard(50,50,5);

    public Game(boolean gameActive, GameBoard gameBoard, List<Flag> flags, HashSet<Player> players) {
        this.flags = flags;
        this.gameActive = gameActive;
        this.players = players;
        this.deadPlayers = new HashSet<>();
        this.rh = new RoundHandler(gameBoard);
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
            rh.dealProgramCards(players);

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

    public void isRobotDead() {
        for (Player player : players) {
            if (player.getRobot().getHealth() < 1) {
                player.decreaseLife();
                if (player.getLife() < 1) {
                    deadPlayers.add(player);
                    players.remove(player);
                    if (players.isEmpty()) {
                        gameActive = false;
                    }
                }
                player.getRobot().resetHealth();
                player.placeRobotAtSpawn(rh.gameBoard);
            }
        }
    }

    /**
     * If debugmode is true:
     * Allows Printing in methods
     */
    private void debugPrint(String debugString) {
        if (debugMode) {
            System.out.println(debugString);
        }
    }
}