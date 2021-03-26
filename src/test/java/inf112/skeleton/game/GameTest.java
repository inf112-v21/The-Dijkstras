package inf112.skeleton.game;
import inf112.skeleton.grid.GameBoard;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class GameTest {
    Game myGame;
    GameBoard<String> myGameBoard;

    @Before
    public void makeGame() {
        myGameBoard = new GameBoard<String>(5, 5, "hey",2);
    }

    @Test
    public void flagTest() {
        assertThat("flag", is("flag"));
    }
}
