package inf112.skeleton.game;

import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.Robot;
import inf112.skeleton.Game.RoundHandler;
import inf112.skeleton.grid.Location;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
public class RoundHandlerTest {
    @Test
    public void hasRoundHandlerADeckOf84CardsTest(){
        RoundHandler rh = new RoundHandler();

        assertThat (rh.deck.cardDeck.size(), is(84));
    }
    @Test
    public void DetermineTheNumberOfCardsTest(){
        RoundHandler rh = new RoundHandler();
        Player player1= new Player(new Location(0,0));
        player1.setRobot(new Robot());

        assertThat(rh.DetermineTheNumberOfCards(player1), is(9));

        player1.announcePowerDown();

        assertThat(rh.DetermineTheNumberOfCards(player1), is(0));

        player1.cancelPowerDown();
        player1.getRobot().getDamage(2);

        assertThat(rh.DetermineTheNumberOfCards(player1), is(7));
    }
    @Test
    public void dealProgramCardsTest(){
        RoundHandler rh = new RoundHandler();

        Player player1= new Player(new Location(0,0));
        player1.setRobot(new Robot());

        Player player2= new Player(new Location(2,0));
        player2.setRobot(new Robot());

        Player player3= new Player(new Location(2,0));
        player3.setRobot(new Robot());

        HashSet<Player> players= new HashSet<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);

        player2.getRobot().getDamage(2);
        player3.announcePowerDown();
        rh.dealProgramCards(players);

        assertThat(player1.getHand().size(), is(9));
        assertThat(player2.getHand().size(), is(7));
        assertThat(player3.getHand().size(), is(0));



    }

}
