package inf112.skeleton.grid;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class GameBoardTest {

    @Test
    public void makeGameBoardTest() {
        Grid grid1 = new Grid(3,3,null,1);
        GameBoard gameboard1 = new GameBoard(3,3,null,3);

        Location loc1 = new Location(2,1, 1);
        String a = "hei";

        grid1.set(loc1, a);
        gameboard1.set(loc1, a);

        assertThat(grid1.get(loc1), is (gameboard1.get(loc1)));
    }

    @Test
    public void testSuperMethods() {
        GameBoard<String> gameboard1 = new GameBoard<>(3,3,null,3);
        assertTrue(gameboard1.validCoordinate(1,1));
        assertFalse(gameboard1.validCoordinate(1,3));

        Location loc1 = new Location(2,1,1);
        Location loc2 = new Location(2,1,2);
        Location loc3 = new Location(1,2,1);
        String a = "hei";

        gameboard1.set(loc1, a);
        Location myloc = gameboard1.locationOf(a);
        System.out.println(myloc.getLayer());
        System.out.println(myloc.getCol());
        System.out.println(myloc.getRow());
        //assertTrue(gameboard1.locationOf(a).equals(loc1));
        //assertFalse(gameboard1.locationOf(a).equals(loc2));
        //assertFalse(gameboard1.locationOf(a).equals(loc3));
    }

    @Test
    public void testCopy() {
        GameBoard gameboard1 = new GameBoard(3,3,null,3);
        Location loc1 = new Location(1,2,1);
        Location loc2 = new Location(2,1,2);
        String a = "hei";

        gameboard1.set(loc1, a);
        gameboard1.set(loc2, a);

        GameBoard gameboard1Copy = gameboard1.copy();
        gameboard1Copy.set(loc1, null);

        assertNotEquals(gameboard1.get(loc1), gameboard1Copy.get(loc1));
        assertEquals(gameboard1.get(loc2), gameboard1Copy.get(loc2));
    }

    @Test
    public void testContains() {
        GameBoard gameboard1 = new GameBoard(3,3,null,3);
        Location loc1 = new Location(1,2,1);
        String a = "hei";
        String b = "hadet bra";
        gameboard1.set(loc1, a);

        assertTrue(gameboard1.contains(a));
        assertFalse(gameboard1.contains(b));
    }
    
    @Test
    public void testGeneral() {
        GameBoard gameboard1 = new GameBoard(3,3,null,3);
        Location loc1 = new Location(1,2,1);
        Location loc2 = new Location(1,2,2);
        String a = "hei";
        String b = "hadet";

        gameboard1.set(loc1, a);
        gameboard1.set(loc2, b);

        assertTrue(gameboard1.sameXYLocation(a,b));
    }
}
