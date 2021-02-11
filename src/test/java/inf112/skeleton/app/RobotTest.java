package inf112.skeleton.app;
import inf112.skeleton.Game.Robot;
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
    public void RobotHasLife(){
        assertThat(myRobot.getLife(), not(0));
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


}
