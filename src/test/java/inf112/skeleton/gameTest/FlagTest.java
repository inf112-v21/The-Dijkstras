package inf112.skeleton.game;
import inf112.skeleton.Game.Flag;
import inf112.skeleton.grid.Location;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class FlagTest {
    private Flag myFlag;

    @Before
    public void makeFlag(){
        myFlag = new Flag(1);
    }

    @Test
    public void flagHasIndex(){
        assertThat(myFlag.getIndex(), is(1));
    }
}
