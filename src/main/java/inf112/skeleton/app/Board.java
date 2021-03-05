package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Board extends InputAdapter implements ApplicationListener  {
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

    private int mapWidth;
    private int mapHeight;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        map = new TmxMapLoader().load("assets/map001.tmx");

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

        int randomNum = (int)Math.floor(Math.random() * spawns.size);
        Vector2 randomSpawn = (spawns.removeIndex(randomNum));              //Getting one random Vector2

        xPos = (int)randomSpawn.x;                                          //Applying random Vector2 as player-spawn position.
        yPos = (int)randomSpawn.y;


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

        Gdx.input.setInputProcessor(this);


    }
    @Override
    public boolean keyUp (int keycode){
        if (keycode== Input.Keys.LEFT && xPos>0){
            playerLayer.setCell(xPos,yPos,null);
            xPos-=1;
        }else if ( keycode == Input.Keys.RIGHT && xPos<mapWidth-1){
            playerLayer.setCell(xPos,yPos,null);
            xPos +=1;
        }else if (keycode== Input.Keys.DOWN && yPos >0){
            playerLayer.setCell(xPos,yPos,null);
            yPos-=1;
        }else if (keycode== Input.Keys.UP && yPos<mapHeight-1){
            playerLayer.setCell(xPos,yPos,null);
            yPos +=1;
        }
        return false;
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
        }
        else if (flagLayer.getCell(xPos,yPos)!= null){
            playerLayer.setCell(xPos,yPos,playerWonCell);
        }else

            playerLayer.setCell(xPos,yPos,playerCell);
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