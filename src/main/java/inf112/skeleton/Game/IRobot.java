package inf112.skeleton.Game;

import inf112.skeleton.grid.Directions;

/**
 * Moveable Robot
 * Intereacting with map and other robots
 */

public interface IRobot extends TileObject{

    int getHealth();

    void addDamage(int damage);

    void setDirection(Directions direction);

    Directions getDirection();


}
