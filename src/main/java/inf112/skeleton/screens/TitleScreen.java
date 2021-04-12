package inf112.skeleton.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.RoboRally;
import inf112.skeleton.app.Board;


public class TitleScreen extends ScreenAdapter {
    private final Stage stage;

    public TitleScreen(RoboRally game) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);

        Texture roboRallyLogoTexture = new Texture(Gdx.files.internal("menuElements/roboRallyLogo.png"));
        Image roboRallyLogo = new Image(roboRallyLogoTexture);

        Skin skin = new Skin(Gdx.files.internal("menuElements/roboRally.json"));
        SelectBox<String> mapChoiceBox = new SelectBox<String>(skin);
        String[] mapChoiceList = {"exampleMap.tmx", "map001.tmx"};
        mapChoiceBox.setItems(mapChoiceList);

        SelectBox<String> playerCountChoice = new SelectBox<String>(skin);
        String[] playerCountList = {"1", "2", "3", "4"};
        playerCountChoice.setItems(playerCountList);

        Button multiPlayerButton = new TextButton("Multiplayer", skin);
        multiPlayerButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Integer playerCount = Integer.valueOf(playerCountChoice.getSelected());
                new Board(game, mapChoiceBox.getSelected(), playerCount);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        Button singlePlayerButton = new TextButton("Singleplayer", skin);
        singlePlayerButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Integer playerCount = 1;
                new Board(game, mapChoiceBox.getSelected(), playerCount);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });



        Button exitButton = new TextButton("Exit Game", skin);
        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                System.exit(0);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        table.add(roboRallyLogo).top().colspan(4).padBottom(100).padTop(50);
        table.row().padBottom(30);

        table.add(singlePlayerButton).prefHeight(50).prefWidth(200).colspan(2);
        table.row().padBottom(30);
        table.add(multiPlayerButton).prefHeight(50).prefWidth(200).colspan(2);
        table.add(mapChoiceBox).colspan(2);
        table.row().padBottom(30);

        table.add(playerCountChoice).colspan(4);
        table.row().padBottom(30);

        table.add(exitButton).prefHeight(50).prefWidth(200).colspan(2);
        table.row().padBottom(30);

        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(112 / 255f, 128 / 255F, 144 / 255F, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}