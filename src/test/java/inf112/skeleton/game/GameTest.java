package inf112.skeleton.game;
import inf112.skeleton.grid.*;
import inf112.skeleton.Game.Game;
import inf112.skeleton.Game.Flag;
import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.Barricade;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class GameTest {
    private Game myGame;
    GameBoard<Object> myGameBoard;
    ArrayList<Flag> flaglist;
    ArrayList<Player> playerlist;

    @Before
    public void makeGame() {
        myGameBoard = new GameBoard<Object>(10, 10, null,5);
        flaglist = new ArrayList<>();
        playerlist = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            flaglist.add(new Flag(new Location(i,i,1),i));
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
        Location loc1 = new Location(2,3, 0);
        Location loc2 = new Location(3,4, 0);

        myGame.wall(loc1, Directions.EAST);
        //assertThat(((Barricade) myGameBoard.get(loc1.move(Directions.EAST))).isFacing(Directions.WEST), is (true));
    }
}
