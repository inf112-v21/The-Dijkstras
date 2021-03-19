package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

public class Flag implements TileObject {
    private final int flagIndex;
    //TODO

    public Flag(int flagindex) { //Start with the first flag having flagIndex = 1
        this.flagIndex = flagindex;
    }

    public boolean playerHasVisited(Player player) {
        return (player.getNextFlagIndex() > flagIndex);
    }

    public int getIndex() {
        return this.flagIndex;
    }

}
