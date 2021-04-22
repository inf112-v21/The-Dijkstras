package inf112.skeleton.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import inf112.skeleton.game.Card;
import inf112.skeleton.game.Player;
import inf112.skeleton.game.Game;
import inf112.skeleton.grid.Directions;

import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameScreen extends ScreenAdapter {
    private final GameInit gameInit;
    private final Board board;
    private Stage stage;

    private Music music;
    private Sound robotMoveSound;
    private Float sfxVolume = 0.3f;
    private Float musicVolume = 0.3f;

    private Viewport viewport;
    private InputMultiplexer inputMultiplexer;
    private GameActor gameActor;
    private HashMap<String, TextureRegionDrawable> cardTexture;
    private ImageButton[] programmingSlotButtons;
    private ImageButton[] handSlotButtons;
    private TextButton startButton;
    private TextButton readyButton;
    private TextButton powerButton;
    private List<Player> players;

    private boolean phasesReady = false;
    private boolean mocMode = true;
    private boolean debugMode = true;   // Change value to "true" to launch with debug mode.
    private TextButton upArrow;
    private TextButton downArrow;
    private TextButton leftArrow;
    private TextButton rightArrow;

    private Game backendGame;
    private HashMap<Integer, Card> chosenCards;
    private HashMap<Integer, Card> currentPlayersHand;

    private int phase;
    private int roundCount = 0;
    int powerDownRound = -10;


    public static void wait(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            // Null
        }
    }

    public GameScreen(GameInit gameInit, Board board, InputMultiplexer inputMultiplexer) {
        this.gameInit = gameInit;
        viewport = new FitViewport(1000, 1000);
        stage = new Stage(viewport);
        this.board = board;
        this.inputMultiplexer = inputMultiplexer;
        inputMultiplexer.addProcessor(stage);

        music = Gdx.audio.newMusic(Gdx.files.internal("assets/audio/gamescreen_music.ogg"));
        music.setLooping(true);
        music.setVolume(musicVolume);
        music.play();


        backendGame = gameInit.getMapBuilder().game;
        chosenCards = new HashMap<>();
        currentPlayersHand = new HashMap<>();
        players = backendGame.getPlayers();


    }

    @Override
    public void show() {
        float unitScale = 1 / 300f;
        Skin skin = new Skin(Gdx.files.internal("assets/menuElements/roboRally.json"));
        TextureAtlas cardsTexAtlas = new TextureAtlas(Gdx.files.internal("assets/cards/cards.atlas"));
        textureLoader(cardsTexAtlas);

        gameActor = new GameActor(gameInit, unitScale * 0.75f);

        robotMoveSound = Gdx.audio.newSound(Gdx.files.internal("assets/audio/robot_sound.ogg"));

        Table programingCardsTable = makeProgrammingCardsTable(skin);
        programingCardsTable.setFillParent(true);
        programingCardsTable.setTransform(true);
        programingCardsTable.left().setY(-330);

        Table cardsTable = makeCardTable(skin);
        cardsTable.setFillParent(true);
        cardsTable.setTransform(true);
        cardsTable.setScale(0.75f);
        cardsTable.left().bottom();

        Table debuggingTable = new Table();
        debuggingTable.setFillParent(true);
        debuggingTable.setTransform(true);
        debuggingTable.right();

        Table frameTable = new Table();
        frameTable.setFillParent(true);
        frameTable.setTransform(true);
        frameTable.right().bottom();


        startButton = new TextButton("Start Round", skin);
        startButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("INFO: Start Round button pressed!");
                startOneRound();

                roundCount++;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        readyButton = new TextButton("Ready?", skin);
        readyButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("INFO: Ready button pressed!");
                List<Card> readyCards = new ArrayList<>();
                for (int i = 0; i < chosenCards.size(); i++) {
                    readyCards.add(chosenCards.get(i));
                }
                backendGame.rh.receiveCards(players.get(0), readyCards);

                phasesReady = true;
                phase = 1;
                chooseCardsManager();

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        powerButton = new TextButton("Power Down?", skin);
        powerButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("INFO: Power down button pressed!");

                powerDownRound = roundCount;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        // DEBUGGING

        if (debugMode) {
            stage.addListener(new InputListener() {
                @Override
                public boolean keyDown(InputEvent event, int keycode) {
                    System.out.println("INFO: " + Input.Keys.toString(keycode) + " key pressed");
                    Gdx.app.log("Image ClickListener", "keyDown. keycode=" + keycode);
                    if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                        backendGame.rh.gameBoard.moveRobot(Directions.NORTH, players.get(0).getRobot());
                        robotMoveSound.play(sfxVolume);
                    } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                        backendGame.rh.gameBoard.moveRobot(Directions.SOUTH, players.get(0).getRobot());
                        robotMoveSound.play(sfxVolume);
                    } else if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                        backendGame.rh.gameBoard.moveRobot(Directions.WEST, players.get(0).getRobot());
                        robotMoveSound.play(sfxVolume);
                    } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                        backendGame.rh.gameBoard.moveRobot(Directions.EAST, players.get(0).getRobot());
                        robotMoveSound.play(sfxVolume);
                    }
                    gameInit.getMapBuilder().updateMap();
                    return false;
                }
            });
        }


        upArrow = new TextButton("W", skin);
        upArrow.setVisible(debugMode);
        upArrow.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("INFO: Up arrow pressed");
                backendGame.rh.gameBoard.moveRobot(Directions.NORTH, players.get(0).getRobot());
                gameInit.getMapBuilder().updateMap();
                robotMoveSound.play(sfxVolume);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        downArrow = new TextButton("S", skin);
        downArrow.setVisible(debugMode);
        downArrow.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backendGame.rh.gameBoard.moveRobot(Directions.SOUTH, players.get(0).getRobot());
                gameInit.getMapBuilder().updateMap();
                robotMoveSound.play(sfxVolume);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        leftArrow = new TextButton("A", skin);
        leftArrow.setVisible(debugMode);
        leftArrow.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("INFO: left arrow pressed");
                //TODO Up button down logic.

                backendGame.rh.gameBoard.moveRobot(Directions.WEST, players.get(0).getRobot());
                gameInit.getMapBuilder().updateMap();
                robotMoveSound.play(sfxVolume);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        rightArrow = new TextButton("D", skin);
        rightArrow.setVisible(debugMode);
        rightArrow.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("INFO: right arrow pressed");
                //TODO Up button down logic.

                backendGame.rh.gameBoard.moveRobot(Directions.EAST, players.get(0).getRobot());
                gameInit.getMapBuilder().updateMap();
                robotMoveSound.play(sfxVolume);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        frameTable.add(startButton);
        frameTable.add(readyButton);
        frameTable.add(powerButton);

        debuggingTable.add(upArrow).colspan(3);
        debuggingTable.row();
        debuggingTable.add(leftArrow);
        debuggingTable.add(downArrow);

        debuggingTable.add(rightArrow);

        stage.addActor(gameActor);

        stage.addActor(debuggingTable);

        stage.addActor(programingCardsTable);
        stage.addActor(cardsTable);

        stage.addActor(frameTable);

        stage.setDebugAll(debugMode); // Debug stage layout boundaries.
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
        handCardsInteractions();

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
        chosenCardInteractions();
        return programmingCardsTable;
    }


    private void handCardsInteractions() {

        for (int i = 0; i < handSlotButtons.length; i++) {
            int j = i;
            handSlotButtons[j].addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    placeCardInProgrammingSlot(j);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
        }
    }

    private void placeCardInProgrammingSlot(int j) {


        if (chosenCards.size() >= 5) return;
        for (int i = 0; i < 5; i++) {
            if (chosenCards.get(i) == null) {
                chosenCards.put(i, currentPlayersHand.remove(j));

                ImageButton.ImageButtonStyle style = handSlotButtons[j].getStyle();
                programmingSlotButtons[i].setStyle(style);
                break;
            }
        }


        handSlotButtons[j].setStyle(setNullStyle());

    }

    private void chosenCardInteractions() {
        for (int i = 0; i < programmingSlotButtons.length; i++) {
            int j = i;
            programmingSlotButtons[i].addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    returnCardToHand(j);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
        }
    }

    private void returnCardToHand(int j) {


        for (int i = 0; i < 9; i++) {
            if (currentPlayersHand.get(i) == null) {
                ImageButton.ImageButtonStyle style = programmingSlotButtons[j].getStyle();
                handSlotButtons[i].setStyle(style);
                programmingSlotButtons[j].setStyle(setNullStyle());
                currentPlayersHand.put(i, chosenCards.remove(j));
                break;
            }
        }

    }

    private Button.ButtonStyle setNullStyle() {
        ImageButton.ImageButtonStyle styleNull = new ImageButton.ImageButtonStyle();
        styleNull.imageUp = cardTexture.get("NULL");
        return styleNull;
    }


    private void updateCards() {
            

        updateProgrammingSlot(players.get(0));

        updatePlayersHand(players.get(0));
    }

    private void updateProgrammingSlot(Player currentPlayer) {

        chosenCards = currentPlayer.getChosenCards();

        for (int i = 0; i < chosenCards.size(); i++) {
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = cardTexture.get(chosenCards.get(4 - i).toString());
            programmingSlotButtons[4 - i].setStyle(style);

        }

    }

    private void updatePlayersHand(Player currentPlayer) {

        currentPlayersHand = new HashMap<>();

        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            currentPlayersHand.put(i, currentPlayer.getHand().get(i));
        }
        int handSize = currentPlayersHand.size();

        for (int i = 0; i < handSize; i++) {
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = cardTexture.get(currentPlayersHand.get(i).toString());
            handSlotButtons[i].setStyle(style);

        }
        for (int i = handSize; i < 9; i++) {
            handSlotButtons[i].setStyle(setNullStyle());
        }
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(150 / 255f, 150 / 255F, 150 / 255F, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (phasesReady) {

            performMovements();

        }

        if (phase == 6) {
            phasesReady = false;
        }


    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    public void startOneRound() {
        if (roundCount == powerDownRound) {
            players.get(0).announcePowerDown(backendGame.rh.gameBoard);
            gameInit.getMapBuilder().updateMap();
        }
        dealCards();
        updateCards();


    }

    public void dealCards() {
        backendGame.rh.dealProgramCards(players);
    }


    private void chooseCardsManager() {

        for (Player player : players) {
            if (!player.isPowerDown()) {
                backendGame.rh.chooseCardsManager(player);
                mocPrint(player.getRobot() + " has chosen: " + player.getChosenCards());
            }
        }

    }


    /**
     * Perform  actions in 5 phases according to programing cards
     */


    public void performMovements() {

        wait(750);
        robotMoveSound.play(sfxVolume);
        List<Player> activePlayer = new ArrayList<>();
        for (Player p : players) {
            if (p.getLife() > 0 && p.getChosenCards().size() == 5)
                activePlayer.add(p);


        }
        backendGame.rh.performOneCardMovement(phase-1, activePlayer);
        programmingSlotButtons[phase - 1].setStyle(setNullStyle());

        gameInit.getMapBuilder().updateMap();
        if (phase == 5) {
            cleanUp();
            collectCards();
            isRobotDead();
            isGameOver();
        }
        phase++;


    }

    public void cleanUp() {
        backendGame.rh.touchCheckpoints(backendGame.getFlags(), players);
        backendGame.rh.cleanUP(players);
        // updateRobotsSpawnPoint(players);
        for (Player p : players)
            updatePlayersHand(p);

    }

    private void collectCards() {
        backendGame.rh.collectCards(players);
        for (Player p : players)
            updatePlayersHand(p);
    }


    public void isGameOver() {
        for (Player p : players) {
            if (p.getNextFlagIndex() > backendGame.getFlags().size()) {
                //gameActive = false;
                mocPrint("Game over, winner is: " + p.getRobot());
            }
        }
    }

    public void isRobotDead() {
        HashSet<Player> deadPlayers = new HashSet<>();
        for (Player player : players) {
            if (player.getRobot().getHealth() < 1) {
                player.decreaseLife();
                if (player.getLife() < 1) {
                    deadPlayers.add(player);
                    players.remove(player);
                    if (players.isEmpty()) {
                        // gameActive = false;
                    }
                }
                player.getRobot().resetHealth();
                player.updateCurrentCards();
                player.placeRobotAtSpawn(backendGame.rh.gameBoard);
            }
        }
    }

    /**
     * If debugmode is true:
     * Allows Printing in methods
     */


    protected void mocPrint(String debugString) {
        if (mocMode) {
            System.out.println(debugString);
        }
    }

}