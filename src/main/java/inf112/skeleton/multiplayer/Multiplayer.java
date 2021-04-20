package inf112.skeleton.multiplayer;

import inf112.skeleton.game.*;
import org.json.*;
import java.util.*;



public class Multiplayer {
    private final Packets packets = new Packets();
    private Server server;
    private HashMap<Integer, Host> hosts;
    private int map;
    private Deck deck;
    private String gamePhase;
    private Map<Integer, List<Card>> playerHands;
    private HashMap<Integer, List<Card>> allPlayerCards;
    private HashMap<Integer, Boolean> allPlayerPowerDown;
    private HashMap<Integer, Boolean> allPlayerReady;
    private int playerAmount;


    Multiplayer(int map, Server server){
        this.map = map;
        this.server = server;
        this.hosts = new HashMap<>();
        this.deck = new Deck();
    }




    public boolean allPlayersReady() {
        boolean statement = true;
        for (int i = 1; i <= playerAmount; i++) {
            if (!allPlayerReady.get(i)) {
                statement = false;
                break;
            }
        }
        return statement;
    }
}
