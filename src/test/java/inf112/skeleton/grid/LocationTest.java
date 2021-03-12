package inf112.skeleton.grid;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationTest {
    @Test
    public void moveTest(){
        Location loc1= new Location(4,4);
        assertThat(loc1.move(Directions.NORTH),is(new Location(4,5)));
        assertThat(loc1.move(Directions.SOUTH),is(new Location(4,3)));

        assertThat(loc1.move(Directions.EAST),is(new Location(5,4)));
        assertThat(loc1.move(Directions.WEST),is(new Location(3,4)));
    }
}
