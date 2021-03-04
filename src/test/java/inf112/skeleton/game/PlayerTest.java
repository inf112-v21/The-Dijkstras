package inf112.skeleton.game;
import inf112.skeleton.Game.Card;
import inf112.skeleton.Game.CardType;
import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.Robot;
import inf112.skeleton.grid.Location;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class PlayerTest {
    public Player myPlayer;

    @Before
    public void MakePlayer(){
        myPlayer = new Player(new Location(0,0));
        myPlayer.setRobot(new Robot());
    }

    @Test
    public void PlayerHasLife(){
        assertThat(myPlayer.getLife(), not(0));
    }

    @Test
    public void PlayerHasCheckPoint(){
        Location startLoc = new Location(0,0);
        assertThat(startLoc, is(myPlayer.getLastCheckPoint()));
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
    public void checkNumberOfDamges(){
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
}

