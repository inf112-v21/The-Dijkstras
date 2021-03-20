package inf112.skeleton.game;
import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.Robot;
import inf112.skeleton.Game.Flag;
import inf112.skeleton.Game.Card;
import inf112.skeleton.Game.CardType;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.Location;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class PlayerTest {
    public Player myPlayer;

    @Before
    public void makePlayer(){
        myPlayer = new Player(new Location(0,0));
        myPlayer.setRobot(new Robot(myPlayer.getSpawnPoint()));
    }

    @Test
    public void playerHasLife(){
        assertThat(myPlayer.getLife(), not(0));
    }

    @Test
    public void playerHasRobotSpawnPoint(){
        Location startLoc = new Location(0,0);
        assertThat(startLoc, is(myPlayer.getSpawnPoint()));
    }

    @Test
    public void nextFlagIndexIncreased(){
        Flag myFlag = new Flag(new Location(0,0),1);
        myPlayer.flagCheck(myFlag);
        assertThat(myPlayer.getNextFlagIndex(), is(2));
    }

    @Test
    public void checkSetNewCheckPoint(){
        Location newCheckPoint = new Location(1,1);
        assertThat(myPlayer.getSpawnPoint(), is(not((newCheckPoint))));
        myPlayer.newCheckPoint(newCheckPoint);
        assertThat(myPlayer.getSpawnPoint(), is(newCheckPoint));
    }

    @Test
    public void checkDecreaseOfLife(){
        myPlayer.decreaseLife();
        assertThat(myPlayer.getLife(), is(2));
    }

    @Test
    public void checkDefaultPowerDownValues(){
        assertThat(myPlayer.isPowerDown(), is(false));

    }
    @Test
    public void checkAnnounceAndCancelPowerDown(){
        myPlayer.announcePowerDown();
        assertThat(myPlayer.isPowerDown(), is(true));

        myPlayer.cancelPowerDown();
        assertThat(myPlayer.isPowerDown(), is(false));
    }

    @Test
    public void checkCancelPowerDown(){
        myPlayer.announcePowerDown();

    }
    @Test
    public void checkNumberOfDamages(){
        assertThat(myPlayer.getNumberOfDamages(), is(0));

        myPlayer.getRobot().getDamage(2);
        assertThat(myPlayer.getNumberOfDamages(), is(2));

    }
    @Test
    public void cardChoiceAmountTest(){

        assertThat(myPlayer.cardChoiceAmount(),is(5));

        myPlayer.getRobot().getDamage(6);
        assertThat(myPlayer.cardChoiceAmount(),is(3));
    }
    @Test
    public void addCurrentCardsTest(){
        assertThat(myPlayer.getCurrentCards().size(),is(0));
        myPlayer.addCurrentCards(new Card(CardType.MOVE1,490),1);
        assertThat(myPlayer.getCurrentCards().size(),is(1));
    }
    @Test
    public void allowedToChooseCardsTest(){
        assertThat(myPlayer.allowedToChooseCards(),is(true));

        myPlayer.addCurrentCards(new Card(CardType.MOVE1,490),1);
        myPlayer.addCurrentCards(new Card(CardType.MOVE2,670),2);
        myPlayer.addCurrentCards(new Card(CardType.MOVE3,790),3);
        myPlayer.addCurrentCards(new Card(CardType.BACKUP,430),4);

        assertThat(myPlayer.allowedToChooseCards(),is(true));

        myPlayer.addCurrentCards(new Card(CardType.MOVE1,500),5);

        assertThat(myPlayer.allowedToChooseCards(),is(false));
    }
    @Test
    public void makeMoveTest(){

        Card move1 = new Card(CardType.MOVE1, 490);
        Card move2 = new Card(CardType.MOVE2, 670);
        Card move3 = new Card(CardType.MOVE3, 790);
        Card backup = new Card(CardType.BACKUP, 430);

        Card rotRight = new Card(CardType.ROTRIGHT, 80);
        Card rotLeft = new Card(CardType.ROTLEFT, 70);
        Card uTurn = new Card(CardType.UTURN, 10);



        myPlayer.makeMove(move1);
        assertThat(myPlayer.getRobot().getPosition(), is(new Location(0,1)));


        myPlayer.makeMove(move2);
        assertThat(myPlayer.getRobot().getPosition(), is(new Location(0,3)));


        myPlayer.makeMove(move3);
        assertThat(myPlayer.getRobot().getPosition(), is(new Location(0,6)));

        myPlayer.makeMove(backup);
        assertThat(myPlayer.getRobot().getPosition(), is(new Location(0,5)));

        // When we add a new robot, the start robot direction is North
        myPlayer.makeMove(rotRight);
        assertThat(myPlayer.getRobot().getDirection(),is (Directions.EAST));

        myPlayer.makeMove(rotLeft);
        assertThat(myPlayer.getRobot().getDirection(),is (Directions.NORTH));

        myPlayer.makeMove(uTurn);
        assertThat(myPlayer.getRobot().getDirection(),is (Directions.SOUTH));


    }
}

