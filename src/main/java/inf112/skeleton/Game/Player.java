package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

public class Player {

    private int life;
    private Location lastCheckPoint;

    public Player(Location startPosition){
        this.life = 3;
        this.lastCheckPoint= startPosition;
    }

    public int getLife() {
        return this.life;
    }

    public void decreaseLife(){
        this.life -= 1;
    }

    public Location getLastCheckPoint() {
        return lastCheckPoint;
    }
}
