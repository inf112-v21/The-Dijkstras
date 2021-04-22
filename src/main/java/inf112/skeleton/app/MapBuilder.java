package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.game.Flag;
import inf112.skeleton.game.Game;
import inf112.skeleton.game.Player;
import inf112.skeleton.game.Robo;
import inf112.skeleton.grid.Directions;
import inf112.skeleton.grid.GameBoard;
import inf112.skeleton.grid.Location;

import java.util.*;


public class MapBuilder implements ApplicationListener, InputProcessor {
    private final TiledMap map;
    public TiledMapTileLayer boardLayer, playerLayer, holeLayer, flagLayer, laserLayer, movementLayer, barriersLayer;
    private int numOfFlags;
    private int playerLimit;
    private final Array<Location> spawns = new Array<>(playerLimit);
    private final HashMap<Integer, Location> flagsLocations = new HashMap<>();
    private final HashMap<Integer, Integer> flagIndex = new HashMap<>();
    public Location playerPos;
    public int xPos = 0, yPos = 0;
    private List<Location> lasers;

    public TiledMapTileLayer.Cell playerCell;
    public TiledMapTileLayer.Cell playerWonCell;
    public TiledMapTileLayer.Cell playerDiedCell;

    public int mapWidth;
    public int mapHeight;

    private final List<Player> players;
    private final List<Flag> flags;
    private final GameBoard gameBoard;
    public final Game game;


    public MapBuilder(String map_filename, int playerLimit) {
        this.playerLimit = playerLimit;
        map = new TmxMapLoader().load(map_filename);
        lasers = new LinkedList<>();

        boardLayer = (TiledMapTileLayer) map.getLayers().get("Board");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        laserLayer = (TiledMapTileLayer) map.getLayers().get("Laser");
        movementLayer = (TiledMapTileLayer) map.getLayers().get("Movement");
        barriersLayer = (TiledMapTileLayer) map.getLayers().get("Barriers");

        mapWidth = boardLayer.getWidth();
        mapHeight = boardLayer.getHeight();

        players = new ArrayList<>(playerLimit);
        flags = new ArrayList<>(numOfFlags);
        gameBoard = new GameBoard(mapHeight, mapWidth, 7);
        game = new Game(false, gameBoard, flags, players);

        // translate  tile Id to flag index
        flagIndex.put(55, 1);
        flagIndex.put(63, 2);
        flagIndex.put(71, 3);
        //TODO add fourth flag, to hashmap. cell.getTile().getId() to find texture value.

        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                TiledMapTileLayer.Cell cell1 = flagLayer.getCell(i, j);          //Parsing flagLayer for flags.
                try {
                    if (cell1.getTile() != null) {
                        numOfFlags += 1;
                        flagsLocations.put(flagIndex.get(cell1.getTile().getId()), new Location(i, j, 4));

                    }
                } catch (Exception e) {
                    //Ignoring exceptions
                }
                TiledMapTileLayer.Cell cell2 = playerLayer.getCell(i, j);        //Parsing playerLayer for spawn-point coordinates.
                try {
                    if (cell2.getTile() != null) {

                        spawns.add(new Location(i, j, 5));
                    }
                } catch (Exception e) {
                    //Ignoring exceptions
                }
            }
        }
        if (spawns.size == 0) {
            spawns.add(new Location(0, 0, 5));
            System.out.println("Couldn't find a spawnpoint, setting spawnposition @ xy(0,0)");
        }
        playersInit();
        flagsInit();
        // spawns[0]
        // spawns[3]
    }

    public void playersInit() {
        //TODO Make PlayerTextureList for 4, player_(num).png files.

        //Integer playerNumID = 0; // Temporary testing
        //String filenameIndex = String.format("player_{0}", playerNumID);
        //TextureRegion[][] playerTextures = TextureRegion.split(new Texture(filenameIndex), 300, 300);  // Testing dynamic version
        TextureRegion[][] playerTextures = TextureRegion.split(new Texture("assets/player/player_0.png"), 300, 300); // Original logic
        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][2]));

        for (int i = 0; i < playerLimit; i++) {
            playerPos=spawns.get(i);
            playerLayer.setCell(playerPos.getCol(), playerPos.getRow(), playerCell);

            Player p = new Player(playerPos);
            p.setRobot(new Robo("robo_" + i));
            p.placeRobotAtSpawn(gameBoard);
            players.add(p);
            //System.out.println(playerPos.toString());

        }


    }
    public void updateMap(){
        for (Player player: players) {
            Directions dir=  player.getRobot().getDirection();
            Location newPos =gameBoard.locationOf(player.getRobot());
            playerLayer.setCell(playerPos.getCol(), playerPos.getRow(), null);

            System.out.println("the old robot pos: x= "+playerPos.getCol()+" y="+playerPos.getRow());
            System.out.println("the new robot pos: x= "+newPos.getCol()+" y="+newPos.getRow());
            System.out.println("player rotation "+playerCell.getRotation());
            playerLayer.setCell(newPos.getCol(),newPos.getRow(), playerCell.setRotation(dir.getDir()));
            System.out.println("player rotation after  "+playerCell.getRotation());
            playerPos= newPos;


/*          if (holeLayer.getCell(xPos,yPos)!= null){
                playerLayer.setCell(xPos,yPos,playerDiedCell);
            }
            else if (flagLayer.getCell(xPos,yPos)!= null){
                playerLayer.setCell(xPos,yPos,playerWonCell);
            }
            else
                playerLayer.setCell(xPos,yPos,playerCell);*/
        }
    }

    public void flagsInit() {

        for (int i : flagsLocations.keySet()) {
            Flag f = new Flag(i);
            gameBoard.set(flagsLocations.get(i), f);
            flags.add(f);
        }

    }

    public List<Player> getPlayers() {
        return players;
    }
    public List<Flag> getFlags() {
        return flags;
    }
    public GameBoard getGameBoard(){
        return gameBoard;
    }

    public TiledMap getMap() {
        return this.map;
    }

    @Override
    public boolean keyDown (int keycode) {

        if (keycode== Input.Keys.LEFT && xPos>0) {
           playerLayer.setCell(xPos,yPos,null);
            xPos-=1;
            System.out.println(xPos);
        }else if ( keycode == Input.Keys.RIGHT && xPos<4) {
            playerLayer.setCell(xPos,yPos,null);
            xPos +=1;
            System.out.println(xPos);
        }else if (keycode== Input.Keys.DOWN && yPos >0) {
            playerLayer.setCell(xPos,yPos,null);
            yPos-=1;
            System.out.println(yPos);
        }else if (keycode== Input.Keys.UP && yPos<4) {
            playerLayer.setCell(xPos,yPos,null);
            yPos +=1;
            System.out.println(yPos);
        }
        return true;
    }
    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}