package inf112.skeleton.grid;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameBoardTest {

    @Test
    public void makeGameBoardTest() {
        Grid grid1 = new Grid(3,3,null,1);
        GameBoard gameboard1 = new GameBoard(3,3,null,3);
        Location loc1 = new Location(2,1);

        String a = "hei";
        grid1.set(loc1, a);
        gameboard1.setCell(loc1, a, 1);

        assertThat(grid1.get(loc1), is (gameboard1.getCell(loc1, 1)));
    }

}
