package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private int life;
    private Location robotSpawnPoint;
    private Robot myRobot;
    private int nextFlagIndex = 1;
    private boolean powerDown= false;

    public Player(Location startPosition){
        this.life = 3;
        this.robotSpawnPoint = startPosition;
    }
    public void setRobot(Robot myRobot){ this.myRobot= myRobot; }

    public Robot getRobot(){ return myRobot; }

    public int getLife() {
        return this.life;
    }

    public int getNextFlagIndex(){
        return nextFlagIndex;
    }

    public void robotOnFlagEvent(Flag flag){
        if (flag.getIndex().equals(nextFlagIndex)){
            this.nextFlagIndex++;
        }
    }

    public void decreaseLife(){
        this.life -= 1;
    }

    public Location getSpawnPoint() {
        return this.robotSpawnPoint;
    }

    public void newCheckPoint(Location location) {
        this.robotSpawnPoint = location;}

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
