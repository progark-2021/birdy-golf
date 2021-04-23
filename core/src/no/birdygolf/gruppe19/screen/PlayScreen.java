package no.birdygolf.gruppe19.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.GameManager;
import no.birdygolf.gruppe19.InputProcessor;
import no.birdygolf.gruppe19.factory.WorldFactory;
import no.birdygolf.gruppe19.level.Level;
import no.birdygolf.gruppe19.system.HoleSystem;
import no.birdygolf.gruppe19.system.LevelSystem;
import no.birdygolf.gruppe19.system.MovementSystem;
import no.birdygolf.gruppe19.system.RenderingObsSystem;
import no.birdygolf.gruppe19.system.RenderingSystem;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import static no.birdygolf.gruppe19.screen.ScreenUtils.createInputListener;

public class PlayScreen extends ScreenAdapter {
    private static PlayScreen instance;
    private final MovementSystem movementSystem;
    private BirdyGolf game;
    private Stage stage;
    private WorldFactory factory;
    private World world;
    private Engine engine;
    private InputMultiplexer inputMultiplexer;
    private FitViewport viewport;
    private FreeTypeFontParameter infoParameter = new FreeTypeFontParameter();
    private BitmapFont infoFont;
    private Label info;
    private Label.LabelStyle infoStyle;

    private float accumulator = 0;
    private Table layout;
    private TextureRegion sound, mute, quit;
    private TextureRegionDrawable soundDrawable, muteDrawable, quitDrawable;
    private ImageButton soundButton, muteButton, quitButton;
    private boolean muted = false;
    private Music music;

    private PlayScreen(BirdyGolf game) {
        this.game = game;
        this.engine = new PooledEngine();
        this.factory = new WorldFactory(engine);
        this.stage = new Stage();
        this.movementSystem = new MovementSystem();

        engine.addSystem(movementSystem);
        engine.addSystem(new RenderingSystem(game.batch));
        engine.addSystem(new RenderingObsSystem(game.camera));
        engine.addSystem(new LevelSystem(factory));
        engine.addSystem(new HoleSystem());
    }

    public static PlayScreen getInstance(BirdyGolf game) {
        if (instance == null) {
            instance = new PlayScreen(game);
        }
        return instance;
    }

    private void nextLevel() {
        if (world != null) {
            world.dispose();
        }
        world = new World(new Vector2(0, 0), true);
        factory.setWorld(world);

        int currentLevel = GameManager.INSTANCE.currentLevel;

        // Change to next level when all players have played
        if (GameManager.INSTANCE.playerTurn == 0) {
            currentLevel = ++GameManager.INSTANCE.currentLevel;
        }

        // Stop game if last level has been played
        if (currentLevel == Level.values().length) {
            music.stop();
            GameManager.INSTANCE.resetGame();
            game.setScreen(HighScoreScreen.getInstance(game));
            return;
        }

        engine.getSystem(LevelSystem.class).initializeLevel(Level.values()[currentLevel]);
        engine.getSystem(MovementSystem.class).fetchGolfBall();
        engine.getSystem(HoleSystem.class).fetchEntities();
    }

    private void createUi() {
        viewport = new FitViewport(480, 800, game.camera);
        stage = new Stage(viewport);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/game_music.mp3"));
        music.play();
        music.setLooping(true);

        sound = new TextureRegion(new Texture("music/sound.png"));
        mute = new TextureRegion(new Texture("music/mute.png"));
        quit = new TextureRegion(new Texture("ui/quit.png"));
        soundDrawable = new TextureRegionDrawable(sound);
        muteDrawable = new TextureRegionDrawable(mute);
        quitDrawable = new TextureRegionDrawable(quit);
        soundButton = new ImageButton(soundDrawable);
        muteButton = new ImageButton(muteDrawable);
        quitButton = new ImageButton(quitDrawable);
        quitButton.addListener(createInputListener(game, QuitGameScreen.getInstance(game)));

        infoParameter.size = 30;
        infoFont = game.font.generateFont(infoParameter);


        infoStyle = new Label.LabelStyle();
        infoStyle.font = infoFont;
        info = new Label("player: score", infoStyle);

        layout = new Table();
        layout.add(muteButton).width(50);
        layout.add(soundButton).width(50).padRight(10);
        layout.add(info).width(250);
        layout.add(quitButton).width(40);


        //initializing the music buttons
        if (!muted) {
            soundButton.setVisible(false);
        } else {
            muteButton.setVisible(false);
        }

        stage.addActor(layout);
        layout.setPosition(
                viewport.getWorldWidth() / 2,
                viewport.getWorldHeight() - 30
        );
    }

    @Override
    public void show() {
        createUi();
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new InputProcessor(engine.getSystem(movementSystem.getClass())));
        Gdx.input.setInputProcessor(inputMultiplexer);

        soundButton.addListener(event -> {
            muted = false;
            music.play();
            music.setLooping(true);
            soundButton.setVisible(false);
            muteButton.setVisible(true);

            return false;
        });

        muteButton.addListener(event -> {
            muted = true;
            music.stop();
            soundButton.setVisible(true);
            muteButton.setVisible(false);

            return false;
        });


        engine.getSystem(MovementSystem.class).setProcessing(true);
        engine.getSystem(RenderingSystem.class).setProcessing(true);
        engine.getSystem(RenderingObsSystem.class).setProcessing(true);
        engine.getSystem(HoleSystem.class).setProcessing(true);

        nextLevel();
    }

    @Override
    public void hide() {
        engine.getSystem(MovementSystem.class).setProcessing(false);
        engine.getSystem(RenderingSystem.class).setProcessing(false);
        engine.getSystem(RenderingObsSystem.class).setProcessing(false);
        engine.getSystem(HoleSystem.class).setProcessing(false);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
        game.camera.update();

        // Run physics calculations on set time steps.
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1 / 60f) {
            world.step(1 / 60f, 6, 2);
            accumulator -= 1 / 60f;
        }
        stage.draw();
        if (engine.getSystem(HoleSystem.class).inHole) {
            engine.getSystem(HoleSystem.class).inHole = false;
            GameManager.INSTANCE.nextPlayer();
            nextLevel();
        }

        switch (GameManager.INSTANCE.currentScore) {
            case 0:
            case 1:
            case 2:
                infoStyle.fontColor = Color.WHITE;
                break;
            case 3:
            case 4:
            case 5:
                infoStyle.fontColor = Color.YELLOW;
                break;
            case 6:
            case 7:
            case 8:
                infoStyle.fontColor = Color.GOLD;
                break;
            case 9:
            case 10:
            case 11:
                infoStyle.fontColor = Color.RED;
                break;
            default:
                infoStyle.fontColor = Color.FIREBRICK;

        }

        if (!GameManager.INSTANCE.playerNames.isEmpty()) {
            String playerName = GameManager.INSTANCE.playerNames.get(GameManager.INSTANCE.playerTurn);
            if (playerName.length() > 8) {
                playerName = playerName.substring(0, 7) + "..";
            }
            info.setText(
                    (playerName.equals("")
                            ? "Player " + (GameManager.INSTANCE.playerTurn + 1)
                            : playerName)
                            + ": "
                            + GameManager.INSTANCE.currentScore
            );
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        music.dispose();

    }
}
