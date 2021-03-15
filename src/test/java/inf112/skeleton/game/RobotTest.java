
package inf112.skeleton.game;
import inf112.skeleton.Game.Robot;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.Location;
import org.junit.*;

import static inf112.skeleton.grid.Directions.NORTH;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RobotTest {
    Robot myRobot;

    @Before
    public void MakeRobot(){
        myRobot = new Robot(new Location(0,0));
    }

    @Test
    public void RobotHasHealth(){
        assertThat(myRobot.getHealth(), not(0));
    }

    @Test
    public void RobotOnStartPosition(){
        Location startLoc = new Location(0,0);
        assertThat(startLoc, is(myRobot.getPosition()));
    }
    @Test
    public void RobotGetDamages(){
        int health= myRobot.getHealth();
        myRobot.getDamage(1);
        assertThat(myRobot.getHealth(), is(health-1));

        health= myRobot.getHealth();
        myRobot.getDamage(2);
        assertThat(myRobot.getHealth(), is(health-2));

    }
    @Test
    public void setDirectionTest(){
        Directions westDir = Directions.WEST;
        myRobot.setDirection(westDir);

        assertThat(myRobot.getDirection(),is(westDir));
    }
    @Test
    public void RobotMovesForward(){
        // Robot has direction North and moving forward
        myRobot.moveForward();
        assertThat(myRobot.getPosition().getCol(),is( 0));
        assertThat(myRobot.getPosition().getRow(),is( 1));
    }

    @Test
    public void RobotMovesBackwards(){
        // Robot has direction North and moving backwards
        myRobot.moveBackward();
        assertThat(myRobot.getPosition().getCol(),is( 0));
        assertThat(myRobot.getPosition().getRow(),is( -1));
    }

    @Test
    public void RobotMovesInDirection(){
        Location endLocation = new Location(1, 0);
        myRobot.moveInDirection(Directions.EAST);
        assertThat(myRobot.getPosition(), is(endLocation));
    }

    @Test
    public void RobotRotates(){
        Directions westDir = Directions.WEST;
        myRobot.rotate(3);
        assertThat(myRobot.getDirection(), is(westDir));
    }
}
