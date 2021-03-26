package inf112.skeleton.gameTest;
import inf112.skeleton.game.*;
import inf112.skeleton.grid.GameBoard;
import inf112.skeleton.grid.Location;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Please save my file
 */



public class PlayerTest {
    public Player myPlayer;
    public GameBoard gb;

    @Before
    public void makePlayer(){
        gb = new GameBoard(50, 50, 5);
        myPlayer = new Player(new Location(0,0, 1));
    }

    @Test
    public void playerHasLife(){
        assertThat(myPlayer.getLife(), not(0));
    }

    @Test
    public void playerHasRobotSpawnPoint(){
        Location startLoc = new Location(0,0, 1);
        assertThat(startLoc, is(myPlayer.getSpawnPoint()));
    }

    @Test
    public void nextFlagIndexIncreased(){
        Flag myFlag = new Flag(1);
        myPlayer.checkFlagIndex(myFlag);
        assertThat(myPlayer.getNextFlagIndex(), is(2));
    }

    @Test
    public void checkSetNewCheckPoint(){
        Location newCheckPoint = new Location(1,1, 1);
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
        myPlayer.announcePowerDown(gb);
        assertThat(myPlayer.isPowerDown(), is(true));

        myPlayer.cancelPowerDown();
        assertThat(myPlayer.isPowerDown(), is(false));
    }

    @Test
    public void checkCancelPowerDown(){
        myPlayer.announcePowerDown(gb);

    }
    @Test
    public void checkNumberOfDamages(){
        assertThat(myPlayer.getNumberOfDamages(), is(0));

        myPlayer.getRobot().addDamage(2);
        assertThat(myPlayer.getNumberOfDamages(), is(2));

    }
    @Test
    public void cardChoiceAmountTest(){

        assertThat(myPlayer.cardChoiceAmount(),is(5));

        myPlayer.getRobot().addDamage(6);
        assertThat(myPlayer.cardChoiceAmount(),is(3));
    }
    @Test
    public void addCurrentCardsTest(){
        assertThat(myPlayer.getChosenCards().size(),is(0));
        myPlayer.addChosenCard(new Card(CardType.MOVE1,490),1);
        assertThat(myPlayer.getChosenCards().size(),is(1));
    }
    @Test
    public void allowedToChooseCardsTest(){
        assertThat(myPlayer.allowedToChooseCards(),is(true));

        myPlayer.addChosenCard(new Card(CardType.MOVE1,490),1);
        myPlayer.addChosenCard(new Card(CardType.MOVE2,670),2);
        myPlayer.addChosenCard(new Card(CardType.MOVE3,790),3);
        myPlayer.addChosenCard(new Card(CardType.BACKUP,430),4);

        assertThat(myPlayer.allowedToChooseCards(),is(true));

        myPlayer.addChosenCard(new Card(CardType.MOVE1,500),5);

        assertThat(myPlayer.allowedToChooseCards(),is(false));
    }

}

