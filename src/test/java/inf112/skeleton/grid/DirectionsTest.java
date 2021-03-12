package inf112.skeleton.grid;
import org.junit.Test;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class DirectionsTest {
    Directions myDir;

    @Before
    public void MakeDirection(){
        myDir = Directions.NORTH;
    }

    @Test
    public void checkFacingNorth(){
        int relativeDirection = 0;
        assertThat(myDir.getDir(), is(relativeDirection));
    }

    @Test
    public void checkRotation(){
        Directions eastDirection = Directions.EAST;
        assertThat(myDir.rotate(1),is(eastDirection));
    }

}
