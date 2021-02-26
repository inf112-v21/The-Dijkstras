package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

public class Flag {

    private final Location location;
    private int flagIndex;

    public Flag(Location location, int flagindex) {
        this.location = location;
        this.flagIndex = flagindex; // TODO Different flagindexes in constructor?
    }

    public Object getIndex() {
        return this.flagIndex;
    }
}
