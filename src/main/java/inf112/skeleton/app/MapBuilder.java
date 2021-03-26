package inf112.skeleton.app;
/**
 * Please save my file
 */


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.grid.Location;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MapBuilder {
    private final TiledMap map;
    public TiledMapTileLayer boardLayer, playerLayer, holeLayer, flagLayer, laserLayer, movementLayer, barriersLayer;
    private int numOfFlags;
    private int playerLimit = 8;
    private final Array<Vector2> spawns = new Array<Vector2>(playerLimit);
    private Vector2 playerPos;
    public int xPos = 0, yPos = 0;
    private List<Location> lasers;

    public TiledMapTileLayer.Cell playerCell;
    public TiledMapTileLayer.Cell playerWonCell;
    public TiledMapTileLayer.Cell playerDiedCell;

    public int mapWidth;
    public int mapHeight;

    public MapBuilder(String map_filename) {
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


        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                TiledMapTileLayer.Cell cell1 = flagLayer.getCell(i, j);          //Parsing flagLayer for flag-count.
                try {
                    if (cell1.getTile() != null) {
                        numOfFlags += 1;
                    }
                } catch (Exception e) {
                    //Ignoring exceptions
                }
                TiledMapTileLayer.Cell cell2 = playerLayer.getCell(i, j);        //Parsing playerLayer for spawn-point coordinates
                try {
                    if (cell2.getTile() != null) {
                        spawns.add(new Vector2(i, j));
                    }
                } catch (Exception e) {
                    //Ignoring exceptions
                }
            }
        }
        if (spawns.size == 0) {
            spawns.add(new Vector2(0, 0));
            System.out.println("Couldn't find a spawnpoint, setting spawnposition @ xy(0,0)");
        }
        Vector2 spawnPoint = spawns.get(0);             //Getting first Vector2 in list

        xPos = (int) spawnPoint.x;                       //Applying Vector2 as player-spawn position.
        yPos = (int) spawnPoint.y;

        TextureRegion[][] playerTextures = TextureRegion.split(new Texture("assets/player.png"), 300, 300);
        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][2]));

        playerPos = new Vector2(xPos, yPos);
        playerLayer.setCell(xPos, yPos, playerCell);

    }

    public TiledMap getMap() {
        return this.map;
    }
}