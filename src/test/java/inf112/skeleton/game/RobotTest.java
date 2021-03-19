
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
        myRobot = new Robot();
    }

    @Test
    public void RobotHasHealth(){
        assertThat(myRobot.getHealth(), not(0));
    }


    @Test
    public void RobotGetDamages(){
        int health= myRobot.getHealth();
        myRobot.addDamage(1);
        assertThat(myRobot.getHealth(), is(health-1));

        health= myRobot.getHealth();
        myRobot.addDamage(2);
        assertThat(myRobot.getHealth(), is(health-2));

    }

    @Test
    public void RobotRotates(){
        Directions westDir = Directions.WEST;
        myRobot.rotate(3);
        assertThat(myRobot.getDirection(), is(westDir));
    }
}
