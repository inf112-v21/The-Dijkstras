package inf112.skeleton.game;
import inf112.skeleton.Game.Player;
import inf112.skeleton.grid.Location;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class PlayerTest {
    Player myPlayer;

    @Before
    public void MakePlayer(){
        myPlayer = new Player(new Location(0,0));
    }

    @Test
    public void PlayerHasLife(){
        assertThat(myPlayer.getLife(), not(0));
    }

    @Test
    public void PlayerHasCheckPoint(){
        Location startLoc = new Location(0,0);
        assertThat(startLoc, is(myPlayer.getLastCheckPoint()));
    }
}
