package inf112.skeleton.game;

import inf112.skeleton.grid.*;

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
