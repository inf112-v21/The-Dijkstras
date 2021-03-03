package inf112.skeleton.app;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.RoboRally;
import inf112.skeleton.screens.TitleScreen;


public class Board extends InputAdapter implements ApplicationListener  {
    private final RoboRally game;

    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap map;
    private TiledMapTileLayer boardLayer, playerLayer, holeLayer, flagLayer, laserLayer, movementLayer, barriersLayer;

    private OrthogonalTiledMapRenderer myRenderer;
    private OrthographicCamera myCam;

    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerWonCell;
    private TiledMapTileLayer.Cell playerDiedCell;

    private Vector2 playerPos;
    private int xPos=0, yPos=0;

    public String mapName;

    public int mapWidth;
    public int mapHeight;



    public Board(RoboRally game, String map_filename) {
        mapName = map_filename;
        this.game = game;
        create();
        render();
        dispose();
        System.out.println(playerPos);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        map = new TmxMapLoader().load(mapName);

        boardLayer= (TiledMapTileLayer) map.getLayers().get("Board");
        playerLayer= (TiledMapTileLayer) map.getLayers().get("Player");
        holeLayer= (TiledMapTileLayer) map.getLayers().get("Hole");
        flagLayer= (TiledMapTileLayer) map.getLayers().get("Flag");
        laserLayer= (TiledMapTileLayer) map.getLayers().get("Laser");
        movementLayer = (TiledMapTileLayer) map.getLayers().get("Movement");
        barriersLayer = (TiledMapTileLayer) map.getLayers().get("Barriers");

        mapWidth = boardLayer.getWidth();
        mapHeight = boardLayer.getHeight();

        Array<Vector2> spawns = new Array<Vector2>();                       //Spawn-points' list.

        for (int i = 0; i < mapWidth; i++){                                 //Parsing playerLayer for spawn-point coordinates.
            for (int j = 0; j < mapHeight; j++) {
                TiledMapTileLayer.Cell cell = playerLayer.getCell(i, j);
                try {
                    if (cell.getTile() != null) {
                        spawns.add(new Vector2(i,j));
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

        xPos = (int)spawnPoint.x;                       //Applying Vector2 as player-spawn position.
        yPos = (int)spawnPoint.y;


        myCam = new OrthographicCamera();
        myCam.setToOrtho(false,mapWidth,mapHeight);
        myCam.position.set(mapWidth/2F, mapHeight/2F, 0.0F);
        myCam.update();

        myRenderer= new OrthogonalTiledMapRenderer(map,1F/300F);
        myRenderer.setView(myCam);

        TextureRegion[][] playerTextures = TextureRegion.split( new Texture("assets/player.png"),300,300);
        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][1]));
        playerWonCell =  new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][2]));

        playerPos = new Vector2(xPos,yPos);
        playerLayer.setCell(xPos, yPos, playerCell);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        myRenderer.render();

        if (holeLayer.getCell(xPos,yPos)!= null){
            playerLayer.setCell(xPos,yPos,playerDiedCell);
        } else if (flagLayer.getCell(xPos,yPos)!= null) {
            playerLayer.setCell(xPos, yPos, playerWonCell);
        } else {
            playerLayer.setCell(xPos,yPos,playerCell);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }


}