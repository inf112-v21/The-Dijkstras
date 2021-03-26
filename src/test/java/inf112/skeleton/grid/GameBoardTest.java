package inf112.skeleton.grid;


import inf112.skeleton.game.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class GameBoardTest {
    GameBoard gb;
    ITileObject myObj1;
    ITileObject myObj2;
    ITileObject myObj3;

    @Before
    public void ConstructTestObjects(){
        gb = new GameBoard(5, 5, 3);
        myObj1 = new Flag(0);
        myObj2 = new Robo();
        myObj3 = new Robo();
    }


    @Test
    public void makeGameBoardTest() {
        Grid<ITileObject> grid1 = new Grid<>(3, 3,null, 1);

        Location loc1 = new Location(2,1, 1);

        grid1.set(loc1, myObj1);
        gb.set(loc1, myObj1);

        grid1.set(loc1, myObj2);
        gb.set(loc1, myObj2);

        assertThat(grid1.get(loc1), is (gb.get(loc1)));
    }
    @Test
    public void setAndGetTest() {
        Location loc1 = new Location(1,2,1);
        gb.set(loc1, myObj1);

        assertThat(gb.get(loc1),is(myObj1));
    }

    @Test
    public void testSuperMethods() {

        Location loc1 = new Location(2,1,1);

        gb.set(loc1, myObj1);
        Location myLoc = gb.locationOf(myObj1);

        assertThat(myLoc,is(loc1));
    }

    @Test
    public void testCopy() {
        Location loc1 = new Location(1,2,1);
        Location loc2 = new Location(2,1,2);

        gb.set(loc1, myObj1);
        gb.set(loc2, myObj1);

        GameBoard gb1Copy = gb.copy();
        gb1Copy.set(loc1, null);

        assertNotEquals(gb.get(loc1), gb1Copy.get(loc1));
        assertEquals(gb.get(loc2), gb1Copy.get(loc2));
    }


    @Test
    public void testContains() {
        Location loc1 = new Location(1,2,1);

        gb.set(loc1, myObj1);

        assertTrue(gb.contains(myObj1));
        assertFalse(gb.contains(myObj2));
    }

    @Test
    public void testGeneral() {
        Location loc1 = new Location(1,2,1);
        Location loc2 = new Location(1,2,2);

        gb.set(loc1, myObj1);
        gb.set(loc2, myObj1);

        assertTrue(gb.sameXYLocation(myObj1, myObj1));
    }

    @Test
    public void getLayersTest() {

        assertThat(gb.getLayers(),is(3));
    }
    @Test
    public void getGridLayerTest() {
        EmptyTile e = new EmptyTile();

        assertThat(gb.getGridLayer(0),is (new Grid<ITileObject>(5,5,e,0)));
    }


    @Test
    public void locationOfTest() {
        Location loc1 = new Location(1,2,1);
        Location loc2 = new Location(2,3,2);

        gb.set(loc1, myObj1);
        gb.set(loc2, myObj2);

        assertThat(gb.locationOf(myObj1),is(loc1));
        assertThat(gb.locationOf(myObj2),is(loc2));


    }

    @Test
    public void containsTest(){
        Location loc1 = new Location(1,2,1);
        gb.set(loc1, myObj1);

        assertThat(gb.contains(myObj1),is(true));
        assertThat(gb.contains(myObj2),is(false));

    }



    @Test
    public void getXYObjectsTest() {
        Location loc1 = new Location(1,2,0);
        Location loc2 = new Location(1,2,1);
        Location loc3 = new Location(1,2,2);
        gb.set(loc1, myObj1);
        gb.set(loc2, myObj2);
        gb.set(loc3, myObj3);

        List<ITileObject> objList= new ArrayList<>();
        objList.add(myObj1);
        objList.add(myObj2);
        objList.add(myObj3);

        assertThat(gb.getXYObjects(new Location(1,2)),is(objList));

    }
    @Test
        public void validCoordinateTest(){

        Location loc1 = new Location(1,2,0);
        Location loc2 = new Location(-1,2,1);

        assertThat(gb.validCoordinate(loc1),is (true));
        assertThat(gb.validCoordinate(loc2),is(false));
    }

    @Test
    public void wallTest() {
        Location loc1 = new Location(2,3, 2);
        Location loc2 = loc1.move(Directions.EAST);

        gb.wall(loc1, Directions.EAST);

        Barricade a = (Barricade) gb.get(loc1);
        Barricade b = (Barricade) gb.get(loc2);
        assertThat(a.isFacing(Directions.EAST), is (true));
        assertThat(b.isFacing(Directions.WEST), is (true));
        assertThat(gb.wallCheck(loc1, Directions.EAST), is (false));
    }
}