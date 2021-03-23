package inf112.skeleton.game;
import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.RoundHandler;
import inf112.skeleton.grid.Location;
import org.junit.Before;
import org.junit.Test;
import java.util.HashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class RoundHandlerTest {
    public RoundHandler rh;
    Player player1;
    Player player2;
    Player player3;
    HashSet<Player> players;

    @Before
    public void setUp(){
        rh = new RoundHandler();
        Location loc1 = new Location(0,0);
        Location loc2 = new Location(2,0);

        player1= new Player(loc1);

        player2= new Player(loc2);

        player3= new Player(loc2);

        players= new HashSet<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);
    }

    @Test
    public void hasFullDeck(){

        assertThat (rh.deck.cardDeck.size(), is(84));
    }
    @Test
    public void correctAmountOfCardsToBeReceived(){

        assertThat(rh.DetermineTheNumberOfCards(player1), is(9));

        player1.announcePowerDown();

        assertThat(rh.DetermineTheNumberOfCards(player1), is(0));

        player1.cancelPowerDown();
        player1.getRobot().addDamage(2);

        assertThat(rh.DetermineTheNumberOfCards(player1), is(7));
    }
    @Test
    public void playerReceivesCorrectAmountOfCards(){

        player2.getRobot().addDamage(2);
        player3.announcePowerDown();
        rh.dealProgramCards(players);

        assertThat(player1.getHand().size(), is(9));
        assertThat(player2.getHand().size(), is(7));
        assertThat(player3.getHand().size(), is(0));

    }


    // Combining JUnit test with hamcrest is a bad idea
    // hamcrest has no assert expression to match exception
    // @Test
    // public void chooseCardManageThrowExceptionTest(){

    //   assertThrows(NoSuchElementException.class,()-> rh.chooseCardsManage(player1)  );
    // }
    @Test
    public void correctAmountOfCardsChosen(){
        //The chosenCards should be empty at the start with round
        assertThat(player1.getChosenCards().size(),is(0));

        player2.getRobot().addDamage(5);
        player3.getRobot().addDamage(7);

        rh.dealProgramCards(players);

        rh.chooseCardsManager(player1);
        rh.chooseCardsManager(player2);
        rh.chooseCardsManager(player3);

        assertThat(player1.getChosenCards().size(),is(5));
        assertThat(player2.getChosenCards().size(),is(4));
        assertThat(player3.getChosenCards().size(),is(2));


    }

}