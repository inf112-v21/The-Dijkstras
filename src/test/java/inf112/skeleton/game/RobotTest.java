
package inf112.skeleton.game;
import inf112.skeleton.Game.Robot;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.Location;
import org.junit.*;
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
    public void RobotMovesForward(){
        Location endLocation = new Location(0, 1);
        myRobot.moveForward();
        assertThat(myRobot.getPosition(), is(endLocation));
    }

    @Test
    public void RobotMovesBackwards(){
        Location endLocation = new Location(0, -1);
        myRobot.moveForward();
        assertThat(myRobot.getPosition(), is(endLocation));
    }

    @Test
    public void RobotRotates(){
        Directions westDir = Directions.WEST;
        myRobot.rotate(2);
        assertThat(myRobot.getDirection(), is(westDir));
    }
}
