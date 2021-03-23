package inf112.skeleton.game;
import inf112.skeleton.Game.RoundHandler;
import inf112.skeleton.Game.Player;
import inf112.skeleton.Game.Robot;
import inf112.skeleton.Game.Card;
import inf112.skeleton.Game.CardType;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.GameBoard;
import inf112.skeleton.grid.Location;
import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.HashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class RoundHandlerPerformMovementsTest {
    public RoundHandler rh;
    Player player1;
    Player player2;
    Player player3;
    HashSet<Player> players;
    GameBoard gb;
    int rows,cols;



    @Before
    public void makeInstances(){
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

        rh = new RoundHandler();
        rh.dealProgramCards(players);

        for (Player p: players){
            p.placeRobotAtSpawn(gb);
            rh.chooseCardsManage(p);
        }

    }
    @Test
    public void performMovementsDoAllProgramingCardsTest(){

        rh.performMovements(players,gb);
        for(Player p:players)
             assertThat(p.getCurrentCards().size(),is(0));
    }
    @ Test
    public void performMovementTest() {
//        System.out.println( "player1 = "+player1.getCurrentCards());
//        System.out.println("player2 = "+player2.getCurrentCards());
//        System.out.println("player3 = "+player3.getCurrentCards());

        HashMap<Player,Location> endLocations= findEndLocationsOfStartLocsAccordingToProgramingCards();

        rh.performMovements(players, gb);

        assertThat(gb.locationOf(player1.getRobot()), is(endLocations.get(player1)));
        assertThat(gb.locationOf(player2.getRobot()), is(endLocations.get(player2)));
        assertThat(gb.locationOf(player3.getRobot()), is(endLocations.get(player3)));
        // This test can get assert feil if there is a collision between two or more robots
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
}
