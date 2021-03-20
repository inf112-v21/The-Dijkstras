package inf112.skeleton.grid;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocationTest {


    Location myLocation;

    @Before
    public void makeLocation(){
        myLocation = new Location(0,0);
    }

    @Test
    public void checkHasLayer(){
        assertThat(myLocation.hasLayer(), not(true));
    }

    @Test
    public void checkSameLocationDifferentLayer() {
        Location myloc1 = new Location(1, 2, 5);
        Location myloc2 = new Location(1,2,3);
        assertThat(myloc1.sameRowCol(myloc2), is(true));
    }



    @Test
    public void checkSameLocation() {
        Location myloc1 = new Location(1, 2, 3);
        Location myloc2 = new Location(1,2,3);
        assertThat(myloc1.equals(myloc2), is(true));
    }
    @Test
    public void moveTest(){

        Location loc1= new Location(4,4);
        Location newLoc;

        newLoc= loc1.move(Directions.NORTH);
        assertThat(newLoc,is(new Location(4,5)));


        newLoc= loc1.move(Directions.SOUTH);
        assertThat(newLoc,is(new Location(4,3)));


        newLoc= loc1.move(Directions.EAST);
        assertThat(newLoc,is(new Location(5,4)));


        newLoc= loc1.move(Directions.WEST);
        assertThat(newLoc,is(new Location(3,4)));

    }

}
