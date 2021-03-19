package inf112.skeleton.game;
import inf112.skeleton.Game.Flag;
import inf112.skeleton.Game.Game;
import inf112.skeleton.grid.GameBoard;
import inf112.skeleton.grid.Location;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class GameTest {
    Game myGame;
    GameBoard myGameBoard;

    @Before
    public void makeGame() {
        Flag myFlag = new Flag(1);
        myGameBoard = new GameBoard(5, 5,2);
    }

    @Test
    public void flagTest() {
        assertThat("flag", is("flag"));
    }
}
