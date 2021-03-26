package inf112.skeleton.gameTest;

import inf112.skeleton.game.*;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.GameBoard;
import inf112.skeleton.grid.Location;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for players move methods
 */
public class PlayerMoveTest {

    public Player myPlayer;
    GameBoard gb;
    Card move1 = new Card(CardType.MOVE1, 490);
    Card move2 = new Card(CardType.MOVE2, 670);
    Card move3 = new Card(CardType.MOVE3, 790);
    Card backup = new Card(CardType.BACKUP, 430);
    Card rotRight = new Card(CardType.ROTRIGHT, 80);
    Card rotLeft = new Card(CardType.ROTLEFT, 70);
    Card uTurn = new Card(CardType.UTURN, 10);


    @Before
    public void makeInstances(){
        myPlayer = new Player(new Location(0,5,2));
        gb = new GameBoard(10,10,5);
        myPlayer.placeRobotAtSpawn(gb);
    }

    @Test
    public void move1Test() {
        myPlayer.makeMove(move1, gb);
        assertThat(gb.locationOf(myPlayer.getRobot()), is(new Location(0, 6,2)));

    }

    @Test
    public void move2Test() {
        myPlayer.makeMove(move2, gb);
        assertThat(gb.locationOf(myPlayer.getRobot()), is(new Location(0, 7,2)));

    }
    @Test
    public void move3Test() {
        myPlayer.makeMove(move3, gb);
        assertThat(gb.locationOf(myPlayer.getRobot()), is(new Location(0, 8,2)));
    }
    @Test
    public void backupTest() {
        myPlayer.makeMove(backup, gb);
        assertThat(gb.locationOf(myPlayer.getRobot()), is(new Location(0, 4,2)));
    }

    @Test
    public void rightRotationTest() {
        // When we add a new robot, the start robot direction is North
        myPlayer.makeMove(rotRight, gb);
        assertThat(myPlayer.getRobot().getDirection(), is(Directions.EAST));
    }

    @Test
    public void leftRotationTest() {
        myPlayer.makeMove(rotLeft,gb);
        assertThat(myPlayer.getRobot().getDirection(),is (Directions.NORTH));
    }

    @Test
    public void uTurnTest() {
        myPlayer.makeMove(uTurn,gb);
        assertThat(myPlayer.getRobot().getDirection(),is (Directions.SOUTH));

    }
}
