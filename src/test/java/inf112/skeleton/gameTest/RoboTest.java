package inf112.skeleton.gameTest;
import inf112.skeleton.game.Robo;
import inf112.skeleton.grid.Directions;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Please save my file
 */


public class RoboTest {
    Robo myRobo;

    @Before
    public void MakeRobot(){
        myRobo = new Robo();
    }

    @Test
    public void RobotHasHealth(){
        assertThat(myRobo.getHealth(), not(0));
    }


    @Test
    public void RobotTakesDamage(){
        int health= myRobo.getHealth();
        myRobo.addDamage(1);
        assertThat(myRobo.getHealth(), is(health-1));

        health= myRobo.getHealth();
        myRobo.addDamage(2);
        assertThat(myRobo.getHealth(), is(health-2));

    }

    @Test
    public void RobotRotates(){
        Directions westDir = Directions.WEST;
        myRobo.rotate(3);
        assertThat(myRobo.getDirection(), is(westDir));
    }
}
