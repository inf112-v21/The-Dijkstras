package inf112.skeleton.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.*;

public class GameScreen extends ScreenAdapter {
    private final GameInit gameInit;
    private final Board board;
    private Stage stage;
    private Viewport viewport;
    private InputMultiplexer inputMultiplexer;
    private GameActor gameActor;

    public GameScreen(GameInit gameInit, Board board, InputMultiplexer inputMultiplexer) {
        this.gameInit = gameInit;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        this.board = board;
        this.inputMultiplexer = inputMultiplexer;
        inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void show() {
        float unitScale = 1/300f;
        Skin skin = new Skin(Gdx.files.internal("assets/menuElements/roboRally.json"));

        gameActor = new GameActor(gameInit, 0.5F);

        Table cardsTable = new Table();
        Label label = new Label("test123", skin);
        cardsTable.add(label).center();

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.addActor(gameActor);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}