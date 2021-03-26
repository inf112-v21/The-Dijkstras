package inf112.skeleton.game;
/**
 * Please save my file
 */


import inf112.skeleton.grid.*;

import java.util.ArrayList;

public class Barricade implements ITileObject{
    private final int layer = 3;
    private Location loc;
    private ArrayList<Directions> facing;

    public Barricade(Location loc, Directions dir) {
        this.loc = loc;
        this.facing = new ArrayList<Directions>();
        this.facing.add(dir);
    }

    public int getLayer() {
        return layer;
    }

    public Location getLocation() {
        return loc;
    }

    public ArrayList<Directions> getFacing() {
        return facing;
    }

    public void addBarricade(Directions dir) {
        if (facing.contains(dir)) {
            return;
        }else {
            facing.add(dir);
        }
    }

    public boolean isFacing(Directions dir) {
        for(Directions face : facing) {
            if (face == dir) { return true; }
        }
        return false;
    }
}
