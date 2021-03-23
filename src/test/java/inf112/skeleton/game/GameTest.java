package inf112.skeleton.game;
import inf112.skeleton.grid.*;
import inf112.skeleton.Game.Game;
import inf112.skeleton.Game.Flag;
import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.Barricade;
import inf112.skeleton.Game.ITileObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class GameTest {
    private Game myGame;
    GameBoard myGameBoard;
    ArrayList<Flag> flaglist;
    ArrayList<Player> playerlist;

    @Before
    public void makeGame() {
        myGameBoard = new GameBoard(10, 10,5);
        flaglist = new ArrayList<>();
        playerlist = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            flaglist.add(new Flag(i));
            playerlist.add(new Player(new Location(i+1,i,2)));
        }
        myGame = new Game(myGameBoard, flaglist, playerlist);
    }

    @Test
    public void flagTest() {
        assertThat("flag", is("flag"));
    }

    @Test
    public void wallTest() {
        Location loc1 = new Location(2,3, 3);
        Location loc2 = loc1.move(Directions.EAST);

        myGame.wall(loc1, Directions.EAST);

        Barricade a = (Barricade) myGame.getGameBoard().get(loc1);
        Barricade b = (Barricade) myGame.getGameBoard().get(loc2);
        assertThat(a.isFacing(Directions.EAST), is (true));
        assertThat(b.isFacing(Directions.WEST), is (true));
        assertThat(myGame.validMove(loc1, Directions.EAST), is (false));
    }
}
