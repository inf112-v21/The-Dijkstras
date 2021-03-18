package inf112.skeleton.Game;

import inf112.skeleton.grid.*;

import java.util.ArrayList;

public class Barricade {
    private final int layer = 3;
    private Location loc;
    private ArrayList<Directions> facing;

    public Barricade(Location loc, Directions dir) {
        this.loc = loc;
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


}
