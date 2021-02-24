package inf112.skeleton.game;
import inf112.skeleton.Game.Card;
import inf112.skeleton.Game.CardType;
import org.junit.Test;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CardTest {

    @Test
    public void makeCardTest(){
        Card card= new Card(CardType.MOVE1,500);

        assertThat(card.type, is( CardType.MOVE1) );
        assertThat(card.priorityNr, is( 500) );
    }
}
