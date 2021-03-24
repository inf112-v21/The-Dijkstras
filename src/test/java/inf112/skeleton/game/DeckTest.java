package inf112.skeleton.game;
import inf112.skeleton.Game.Card;
import inf112.skeleton.Game.CardType;
import inf112.skeleton.Game.Deck;
import org.junit.Test;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Deck of Cards
 *
 * Consists of:
 * backup: 6 kort (430 - 480)
 * u-turn: 6 kort (10 - 60)
 * rotate right: 18 kort (80-420, intervall 20
 * rotate left: 18 kort (70-410, intervall 20)
 * move 1: 18 kort (490 - 650, intervall 10)
 * move 2: 12 kort (670 - 780, intervall 10)
 * move 3: 6 kort (790 - 840, intervall 10)
 */



public class DeckTest {
    public Deck myDeck;

    @Before
    public void initDeck(){
        myDeck = new Deck();
    }


    @Test
    public void isDeckNotEmpty(){

        assertThat(myDeck.cardDeck.size(), is(84));
    }


    @Test
    public void has6backUpCards(){

        assertThat(cardCounter(CardType.BACKUP) , is((6)));


    }
    @Test
    public void has6UTurnCards(){

        assertThat(cardCounter(CardType.UTURN), is(6));
    }


    @Test
    public void has18RotateRight(){

        assertThat(cardCounter(CardType. ROTRIGHT), is(18));
    }
    @Test
    public void has18RotateLeft(){

        assertThat(cardCounter(CardType.ROTLEFT), is(18));
    }
    @Test
    public void has18Move1(){

        assertThat(cardCounter(CardType.MOVE1), is(18));
    }
    @Test
    public void has12Move2(){

        assertThat(cardCounter(CardType.MOVE2) , is(12));
    }
    @Test
    public void has6Move3(){

        assertThat(cardCounter(CardType.MOVE3), is(6));
    }

    private int cardCounter(CardType myCardType){
        int myCount = 0;
        for(Card card: myDeck.cardDeck){
            if (card.type.equals(myCardType))
                myCount++;
        }
        return myCount;
    }
}
