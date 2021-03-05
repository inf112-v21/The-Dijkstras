package inf112.skeleton.game;
import inf112.skeleton.Game.Flag;
import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.Robot;
import inf112.skeleton.grid.Location;
import org.junit.*;
import org.lwjgl.system.CallbackI;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class PlayerTest {
    Player myPlayer;

    @Before
    public void makePlayer(){
        myPlayer = new Player(new Location(0,0));
        myPlayer.setRobot(new Robot());
    }

    @Test
    public void playerHasLife(){
        assertThat(myPlayer.getLife(), not(0));
    }

    @Test
    public void playerHasRobotSpawnPoint(){
        Location startLoc = new Location(0,0);
        assertThat(startLoc, is(myPlayer.getSpawnPoint()));
    }

    @Test
    public void nextFlagIndexIncreased(){
        Flag myFlag = new Flag(new Location(0,0),1);
        myPlayer.robotOnFlagEvent(myFlag);
        assertThat(myPlayer.getNextFlagIndex(), is(2));
    }

    @Test
    public void checkSetNewCheckPoint(){
        Location newCheckPoint = new Location(1,1);
        myPlayer.newCheckPoint(newCheckPoint);
        assertThat(myPlayer.getSpawnPoint(), is(newCheckPoint));
    }

    @Test
    public void checkDecreaseOfLife(){
        myPlayer.decreaseLife();
        assertThat(myPlayer.getLife(), is(2));
    }

    @Test
    public void checkDefaultPowerDownValues(){
        assertThat(myPlayer.isPowerDown(), is(false));
        
    }
    @Test
    public void checkAnnounceAndCancelPowerDown(){
        myPlayer.announcePowerDown();
        assertThat(myPlayer.isPowerDown(), is(true));

        myPlayer.cancelPowerDown();
        assertThat(myPlayer.isPowerDown(), is(false));
    }

    @Test
    public void checkCancelPowerDown(){
        myPlayer.announcePowerDown();

    }
    @Test
    public void checkNumberOfDamages(){
        assertThat(myPlayer.getNumberOfDamages(), is(0));

        myPlayer.getRobot().getDamage(2);
        assertThat(myPlayer.getNumberOfDamages(), is(2));

    }
}
