package inf112.skeleton.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.*;

import java.util.HashMap;

public class GameScreen extends ScreenAdapter {
    private final GameInit gameInit;
    private final Board board;
    private Stage stage;
    private Viewport viewport;
    private InputMultiplexer inputMultiplexer;
    private GameActor gameActor;
    private HashMap<String, TextureRegionDrawable> cardTexture;
    private ImageButton[] programmingSlotButtons;
    private ImageButton[] handSlotButtons;
    private TextButton readyButton;
    public GameScreen(GameInit gameInit, Board board, InputMultiplexer inputMultiplexer) {
        this.gameInit = gameInit;
        viewport = new FitViewport(1000, 1000);
        stage = new Stage(viewport);
        this.board = board;
        this.inputMultiplexer = inputMultiplexer;
        inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void show() {
        float unitScale = 1/300f;
        Skin skin = new Skin(Gdx.files.internal("assets/menuElements/roboRally.json"));
        TextureAtlas cardsTexAtlas = new TextureAtlas(Gdx.files.internal("assets/cards/cards.atlas"));
        textureLoader(cardsTexAtlas);

        gameActor = new GameActor(gameInit, unitScale*0.75f);

        Table programingCardsTable = makeProgrammingCardsTable(skin);
        programingCardsTable.setFillParent(true);
        programingCardsTable.setTransform(true);
        programingCardsTable.left().setY(-330);

        Table cardsTable = makeCardTable(skin);
        cardsTable.setFillParent(true);
        cardsTable.setTransform(true);
        cardsTable.setScale(0.75f);
        cardsTable.left().bottom();

        Table frameTable = new Table();
        frameTable.setFillParent(true);
        frameTable.setTransform(true);
        frameTable.right().bottom();

        readyButton = new TextButton("Ready?", skin);
        readyButton.addListener(new InputListener() {
           @Override
           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               System.out.println("INFO: Ready button pressed!");
               //TODO Check cards after making all card choices.
           }
           @Override
           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               return true;
           }
        });
        frameTable.add(readyButton);

        stage.addActor(gameActor);
        stage.addActor(programingCardsTable);
        stage.addActor(cardsTable);
        stage.addActor(frameTable);
        stage.setDebugAll(false);                        // Set to true to debug layout boundaries.

    }

    private void textureLoader(TextureAtlas cardsTexAtlas) {
        cardTexture = new HashMap<>();
        cardTexture.put("MOVE1", new TextureRegionDrawable(cardsTexAtlas.findRegion("card_move1")));
        cardTexture.put("MOVE2", new TextureRegionDrawable(cardsTexAtlas.findRegion("card_move2")));
        cardTexture.put("MOVE3", new TextureRegionDrawable(cardsTexAtlas.findRegion("card_move3")));
        cardTexture.put("BACKUP", new TextureRegionDrawable(cardsTexAtlas.findRegion("card_backup")));
        cardTexture.put("ROTLEFT", new TextureRegionDrawable(cardsTexAtlas.findRegion("card_rotleft")));
        cardTexture.put("ROTRIGHT", new TextureRegionDrawable(cardsTexAtlas.findRegion("card_rotright")));
        cardTexture.put("UTURN", new TextureRegionDrawable(cardsTexAtlas.findRegion("card_uturn")));
        cardTexture.put("NULL", new TextureRegionDrawable(cardsTexAtlas.findRegion("card_null")));

    }

    private Table makeCardTable(Skin skin) {
        //TODO ADD BACK-END FUNCTION TO ADD CARDS.
        Table cardsTable = new Table();

        Label cardsLabel = new Label("Hand: ", skin);

        cardsTable.add(cardsLabel);
        cardsTable.row();

        TextureRegionDrawable nullCard = cardTexture.get("NULL");
        handSlotButtons = new ImageButton[9];

        for (int i = 0; i < 9; i++) {
            ImageButton handSlot = new ImageButton(nullCard);
            cardsTable.add(handSlot);
            handSlotButtons[i] = handSlot;
        }
        cardInteractions();

        return cardsTable;
    }
    private Table makeProgrammingCardsTable(Skin skin) {
        Table programmingCardsTable = new Table();
        Label programmingLabel = new Label("Programming Slots: ", skin);

        programmingCardsTable.add(programmingLabel).colspan(3);
        programmingCardsTable.row();

        TextureRegionDrawable nullCard = cardTexture.get("NULL");
        programmingSlotButtons = new ImageButton[5];

        for (int i = 0; i < 5; i++) {
            ImageButton programmingSlot = new ImageButton(nullCard);
            programmingCardsTable.add(programmingSlot).prefHeight(10).prefWidth(60);
            programmingSlotButtons[i] = programmingSlot;
        }
        cardInteractions1();
        return programmingCardsTable;
    }
    private void cardInteractions() {
        for (int i = 0; i < handSlotButtons.length; i++) {
            int j = i;
            handSlotButtons[i].addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println(("INFO: Card slot: %s pressed").formatted(j));
                }
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
        }
    }
    private void cardInteractions1() {
        for (int i = 0; i < programmingSlotButtons.length; i++) {
            int j = i;
            programmingSlotButtons[i].addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println(("INFO: Programming card slot: %s pressed").formatted(j));
                }
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
        }
    }


    private void updateCards() {
        for (int i = 0; i < 5; i++) {
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = cardTexture.get("NULL");
            //TODO "NULL" only temporary, update with actual cards
            programmingSlotButtons[i].setStyle(style);

        }

        for (int i = 0; i < 9; i++) {
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = cardTexture.get("NULL");
            //TODO "NULL" only temporary, update with actual cards
            handSlotButtons[i].setStyle(style);
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(150/255f, 150/255F, 150/255F, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        updateCards();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
    }
}