package inf112.skeleton.Game;

import inf112.skeleton.grid.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameBoard<Object> gameboard;
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
        List<Object> XYObjects = gameboard.getXYObjects(loc2);
        for (Object obj : XYObjects) {
            if (obj instanceof Barricade) {
                if (((Barricade) obj).isFacing(dir.rotate(2))) {return false;}
            }
        }
        return true;
    }

    public void wall(Location loc, Directions dir) {
        if (gameboard.get(loc) instanceof Barricade) {
            Barricade b = (Barricade) gameboard.get(loc);
            b.addBarricade(dir);
            gameboard.set(loc, b);
        } else {
            gameboard.set(loc, new Barricade(loc, dir));
        }
        Location loc2 = loc.move(dir);
        Directions dir2 = dir.rotate(2);
        if (gameboard.get(loc2) instanceof Barricade) {
            Barricade b = (Barricade) gameboard.get(loc2);
            b.addBarricade(dir2);
            gameboard.set(loc2, b);
        } else {
            gameboard.set(loc, new Barricade(loc2, dir2));
        }

    }

    public void flagCheck() {
        for (Player player : players) {
            for (Flag flag : flags) {
                if (gameboard.sameXYLocation(player, flag)) {
                    player.checkFlagIndex(flag);
                }
            }
        }
    }
}