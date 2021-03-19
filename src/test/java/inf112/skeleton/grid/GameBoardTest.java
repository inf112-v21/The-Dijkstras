package inf112.skeleton.grid;

import inf112.skeleton.Game.Flag;
import inf112.skeleton.Game.TileObject;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class GameBoardTest {
    GameBoard gameboard1;
    TileObject myobj;
    TileObject myobj2;

    @Before
    public void ConstructTestObjects(){
        gameboard1 = new GameBoard(3, 3, 3);
        myobj = new Flag(0);
        myobj2 = new Flag(1);
    }


    @Test
    public void makeGameBoardTest() {
        Grid<TileObject> grid1 = new Grid<>(3, 3,null, 1);

        Location loc1 = new Location(2,1, 1);

        grid1.set(loc1, myobj);
        gameboard1.set(loc1, myobj);

        grid1.set(loc1, myobj);
        gameboard1.set(loc1, myobj);

        assertThat(grid1.get(loc1), is (gameboard1.get(loc1)));
    }

    @Test
    public void testSuperMethods() {

        Location loc1 = new Location(2,1,1);

        gameboard1.set(loc1, myobj);
        Location myloc = gameboard1.locationOf(myobj);
        System.out.println(myloc.getLayer());
        System.out.println(myloc.getCol());
        System.out.println(myloc.getRow());
        //assertTrue(gameboard1.locationOf(a).equals(loc1));
        //assertFalse(gameboard1.locationOf(a).equals(loc2));
        //assertFalse(gameboard1.locationOf(a).equals(loc3));
    }

    @Test
    public void testCopy() {
        Location loc1 = new Location(1,2,1);
        Location loc2 = new Location(2,1,2);

        gameboard1.set(loc1, myobj);
        gameboard1.set(loc2, myobj);

        GameBoard gameboard1Copy = gameboard1.copy();
        gameboard1Copy.set(loc1, null);

        assertNotEquals(gameboard1.get(loc1), gameboard1Copy.get(loc1));
        assertEquals(gameboard1.get(loc2), gameboard1Copy.get(loc2));
    }

    @Test
    public void testContains() {
        GameBoard gameboard1 = new GameBoard(3,3,3);
        Location loc1 = new Location(1,2,1);

        gameboard1.set(loc1, myobj);

        assertTrue(gameboard1.contains(myobj));
        assertFalse(gameboard1.contains(myobj));
    }
    
    @Test
    public void testGeneral() {
        GameBoard gameboard1 = new GameBoard(3,3,3);
        Location loc1 = new Location(1,2,1);
        Location loc2 = new Location(1,2,2);

        gameboard1.set(loc1, myobj);
        gameboard1.set(loc2, myobj);

        assertTrue(gameboard1.sameXYLocation(myobj,myobj));
    }
}