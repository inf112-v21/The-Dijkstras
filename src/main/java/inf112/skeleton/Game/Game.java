package inf112.skeleton.Game;

import inf112.skeleton.grid.*;
import java.util.List;

public class Game {
    private boolean gameActive;
    private GameBoard gameboard;
    private final List<Flag> flags;
    private final List<Player> players;

    public Game(GameBoard gameboard, List<Flag> flags, List<Player> players) {
        this.gameboard = gameboard;
        this.flags = flags;
        this.players = players;
    }

    public GameBoard getGameBoard() { return gameboard; }

    public boolean validMove(Location loc, Directions dir) {
        Location loc2 = loc.move(dir);
        List<ITileObject> XYObjects = gameboard.getXYObjects(loc2);
        for (Object obj : XYObjects) {
            if (obj instanceof Barricade) {
                if (((Barricade) obj).isFacing(dir.rotate(2))) {return false;}
            }
        }
        return true;
    }

    private void placeBarricade(Location loc, Directions dir) {
        if (gameboard.get(loc) instanceof Barricade) {
            Barricade b = (Barricade) gameboard.get(loc);
            b.addBarricade(dir);
            gameboard.set(loc, b);
        } else {
            gameboard.set(loc, new Barricade(loc, dir));
        }
    }

    public void wall(Location loc, Directions dir) {
        placeBarricade(loc, dir);
        Location loc2 = loc.move(dir);
        Directions dir2 = dir.rotate(2);
        placeBarricade(loc2, dir2);
    }

    public void flagCheck() {
        for (Player player : players) {
            for (Flag flag : flags) {
                if (gameboard.sameXYLocation(player.getRobot(), flag)) {
                    player.checkFlagIndex(flag);
                }
            }
        }
    }
}