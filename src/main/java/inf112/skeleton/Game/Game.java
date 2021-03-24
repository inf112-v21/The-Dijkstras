package inf112.skeleton.Game;

import inf112.skeleton.grid.*;
import java.util.HashSet;
import java.util.List;

public class Game {
    private GameBoard gameboard;
    private final List<Flag> flags;
    private final HashSet<Player> players;

    public Game(GameBoard gameboard, List<Flag> flags, HashSet<Player> players) {
        this.gameboard = gameboard;
        this.flags = flags;
        this.players = players;
    }


}
