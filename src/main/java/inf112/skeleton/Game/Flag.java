package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

public class Flag {

    private final Location location;
    private final int flagIndex;

    public Flag(Location location, int flagindex) {
        this.location = location;
        this.flagIndex = flagindex;
    }


    public Object getIndex() {
        return this.flagIndex;
    }

}
