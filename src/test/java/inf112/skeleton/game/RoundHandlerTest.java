package inf112.skeleton.game;
import inf112.skeleton.Game.Card;
import inf112.skeleton.Game.CardType;
import inf112.skeleton.Game.Flag;
import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.RoundHandler;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.GameBoard;
import inf112.skeleton.grid.Location;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class RoundHandlerTest {
    public RoundHandler rh;
    Player player1;
    Player player2;
    Player player3;
    HashSet<Player> players;
    List<Flag> flags;
    GameBoard gb;
    int rows,cols;
    @Before
    public void setUp(){
        rows=50; cols=50;
        gb = new GameBoard(rows,cols,5);

        player1 = new Player(new Location(2,0,2));
        player2 = new Player(new Location(20,0,2));
        player3 = new Player(new Location(48,0,2));

        player1.setRobot(new Robot("robot1"));
        player2.setRobot(new Robot("robot2"));
        player3.setRobot(new Robot("robot3"));

        players= new HashSet<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        for (Player p: players)  p.placeRobotAtSpawn(gb);

        Flag flag1 = new Flag(1);
        Flag flag2 = new Flag(2);
        flags= new ArrayList<>();
        flags.add(flag1);
        flags.add(flag2);
        gb.set(new Location(5,5,1),flag1);
        gb.set(new Location(10,5,1),flag2);
        rh = new RoundHandler(gb, flags, players);

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
        rh.dealProgramCards();

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

    /**
     * Checks if players chooses the correct amount of cards based on amount of lifes left.
     *
     */
    @Test
    public void correctAmountOfCardsChosen(){
        //The chosenCards should be empty at the start with round
        assertThat(player1.getChosenCards().size(),is(0));

        player2.getRobot().addDamage(5);
        player3.getRobot().addDamage(7);

        rh.dealProgramCards();

        rh.chooseCardsManager(player1);
        rh.chooseCardsManager(player2);
        rh.chooseCardsManager(player3);

        assertThat(player1.getChosenCards().size(),is(5));
        assertThat(player2.getChosenCards().size(),is(4));
        assertThat(player3.getChosenCards().size(),is(2));


    }
    @ Test
    public void performMovementTest() {
//        System.out.println( "player1 = "+player1.getCurrentCards());
//        System.out.println("player2 = "+player2.getCurrentCards());
//        System.out.println("player3 = "+player3.getCurrentCards());

        dealAndChooseCards();
        HashMap<Player,Location> endLocations= findEndLocationsOfStartLocsAccordingToProgramingCards();

        rh.performMovements();

        assertThat(gb.locationOf(player1.getRobot()), is(endLocations.get(player1)));
        assertThat(gb.locationOf(player2.getRobot()), is(endLocations.get(player2)));
        assertThat(gb.locationOf(player3.getRobot()), is(endLocations.get(player3)));
        // This test can get assert feil if there is a collision between two or more robots
    }
    private void dealAndChooseCards(){
        rh.dealProgramCards();
        for (Player p: players){
            rh.chooseCardsManage(p);
        }
    }

    private HashMap<Player, Location> findEndLocationsOfStartLocsAccordingToProgramingCards() {
        HashMap<Player,Location> endLocations= new HashMap<>();
        for (Player p : players) {
            HashMap<Integer, Card> playerCards = p.getCurrentCards();
            Location loc = gb.locationOf(p.getRobot());
            Directions dir = p.getRobot().getDirection();

            for (int i = 1; i <= 5; i++) {
                Card card = playerCards.get(i);
                if (card.type == CardType.ROTRIGHT) dir = dir.rotate(1);
                if (card.type == CardType.ROTLEFT) dir = dir.rotate(-1);
                if (card.type == CardType.UTURN) dir = dir.rotate(2);

                if (card.type == CardType.BACKUP) loc = move(loc, dir.rotate(2), 1);
                if (card.type == CardType.MOVE1) loc = move(loc, dir, 1);
                if (card.type == CardType.MOVE2) loc = move(loc, dir, 2);
                if (card.type == CardType.MOVE3) loc = move(loc, dir, 3);
            }
            endLocations.put(p,loc);
        }
        return endLocations;
    }

    private Location move(Location loc, Directions dir,int amountMoving) {
        while (amountMoving>0) {
            Location endLoc= loc.move(dir);
            if(endLoc.getRow()<rows && endLoc.getRow()>=0 && endLoc.getCol()<cols&& endLoc.getCol()>=0)
            { loc = endLoc;}
            amountMoving--;
        }
        return loc;
    }

    @Test
    public void cleanUpLockeCardsTest(){
        dealAndChooseCards();
        player1.getRobot().addDamage(4);
        player2.getRobot().addDamage(5);
        player3.getRobot().addDamage(7);
        rh.cleanUP();

        assertThat(player1.getCurrentCards().size(),is(0));
        assertThat(player2.getCurrentCards().size(),is(1));
        assertThat(player3.getCurrentCards().size(),is(3));

    }

    @Test
    public void touchCheckpointsFlagCheckTest() {

        assertThat(player1.getNextFlagIndex(),is(1));

        gb.set(new Location(2,1,1),flags.get(0));

        Card move1= new Card(CardType.MOVE1,490);
        player1.makeMove(move1,gb);

        rh.touchCheckpoints();

        assertThat(player1.getNextFlagIndex(),is(2));

    }
    @Test
    public void collectCardsTest(){

        dealAndChooseCards();
        assertThat(rh.deck.cardDeck.size(),is(84-3*9));

        rh.collectCards();
        assertThat(rh.deck.cardDeck.size(),is(84));
    }
}