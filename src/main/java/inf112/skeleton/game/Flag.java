package inf112.skeleton.game;

import inf112.skeleton.grid.Location;

public class Flag {

    private final Location location;
    private final int flagIndex;

    public Flag(Location location, int flagindex) { //Start with the first flag having flagIndex = 1
        this.location = location;
        this.flagIndex = flagindex;
    }

    public boolean playerHasVisited(Player player) {
        return (player.getNextFlagIndex() > flagIndex);
    }

    public Location getLocation(){
        return location;
    }

    public int getIndex() {
        return this.flagIndex;
    }

}
