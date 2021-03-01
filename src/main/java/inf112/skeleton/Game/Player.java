package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

public class Player {

    private int life;
    private Location lastCheckPoint;
    private Robot myRobot;

    private boolean powerDown= false;

    public Player(Location startPosition){
        this.life = 3;
        this.lastCheckPoint= startPosition;
    }
    public void setRobot(Robot myRobot){ this.myRobot= myRobot; }

    public Robot getRobot(){ return myRobot; }

    public int getLife() {
        return this.life;
    }

    public void decreaseLife(){
        this.life -= 1;
    }

    public Location getLastCheckPoint() {
        return lastCheckPoint;
    }

    public boolean isPowerDown() {
        return powerDown;
    }
    public void announcePowerDown(){
        powerDown= true;
    }
    public void cancelPowerDown(){
        powerDown= false;
    }
    public int getNumberOfDamages(){
        return 8-myRobot.getHealth();
    }
}
