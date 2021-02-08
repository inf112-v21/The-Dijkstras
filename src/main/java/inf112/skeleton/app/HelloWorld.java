package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class HelloWorld implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap map;
    private TiledMapTileLayer boardLayer, playerLayer, holeLayer, flagLayer;
    private OrthogonalTiledMapRenderer myRenderer;
    private OrthographicCamera myCam;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        map = new TmxMapLoader().load("assets/exampleMap.tmx");
        boardLayer= (TiledMapTileLayer) map.getLayers().get("Board");
        playerLayer= (TiledMapTileLayer) map.getLayers().get("Player");
        holeLayer= (TiledMapTileLayer) map.getLayers().get("Hole");
        flagLayer= (TiledMapTileLayer) map.getLayers().get("Flag");
        font.setColor(Color.RED);
        myCam = new OrthographicCamera();
        myCam.setToOrtho(false,5,5);
        myCam.position.set(2.5F, 5.0F, 0.0F);
        myCam.update();
        myRenderer= new OrthogonalTiledMapRenderer(map,1F/300F);
        myRenderer.setView(myCam);
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
