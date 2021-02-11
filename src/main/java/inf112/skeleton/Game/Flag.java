package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

public class Flag {

    private final Location location;
    private int flagIndex;

    public Flag(Location location) {
        this.location = location;
        this.flagIndex = 1; // TODO Different flagindexes in constructor?
    }

    public Object getIndex() {
        return this.flagIndex;
    }
}
