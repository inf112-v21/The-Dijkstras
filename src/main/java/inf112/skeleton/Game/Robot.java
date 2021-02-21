package inf112.skeleton.Game;

import inf112.skeleton.grid.Location;

/**
 * Robot Class
 * - Has 3 lives. Game over if 0
 * - Has 8 health. Loss life if 0
 * - StartPosition = (0,0)
 */
public class Robot {
    private float life;
    private float health;
    private Location position;
    // TODO Implement Direction

    public Robot() {
        this.life = 3;
        this.health = 8;
        this.position= new Location(0,0);
        // this.direction = new Directions(0, -1); // North == (0, -1)?

    }

    public float getLife() {
        return this.life;
    }

    public float getHealth() {
        return health;
    }

    public Location getPosition() {
        return this.position;
    }
}
