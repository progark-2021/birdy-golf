package no.birdygolf.gruppe19.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.InputProcessor;
import no.birdygolf.gruppe19.GameManager;
import no.birdygolf.gruppe19.factory.WorldFactory;
import no.birdygolf.gruppe19.levels.Level;
import no.birdygolf.gruppe19.systems.BoundsSystem;
import no.birdygolf.gruppe19.systems.LevelSystem;
import no.birdygolf.gruppe19.systems.MovementSystem;
import no.birdygolf.gruppe19.systems.RenderingSystem;

public class PlayScreen extends ScreenAdapter {

    BirdyGolf game;
    Stage stage;
    WorldFactory world;
    PooledEngine engine;
    World physicsWorld;
    InputMultiplexer inputMultiplexer;
    private MovementSystem movementSystem;
    private float elapsedTime = 0;

    public PlayScreen(BirdyGolf game) {
        this.game = game;
        this.engine = new PooledEngine();
        this.world = new WorldFactory(engine);
        this.stage = new Stage();
        this.movementSystem = new MovementSystem();
        this.physicsWorld= new World(new Vector2(0, 0), true);


        engine.addSystem(movementSystem);
        engine.addSystem(new RenderingSystem(game.batch));
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new LevelSystem(world));
        engine.getSystem(MovementSystem.class).setProcessing(true);
        engine.getSystem(RenderingSystem.class).setProcessing(true);
        engine.getSystem(BoundsSystem.class).setProcessing(true);

        engine.getSystem(LevelSystem.class).initializeLevel(Level.LEVEL_1);
    }

    private void nextLevel() {
        int currentLevel = ++GameManager.INSTANCE.currentLevel;
        if (currentLevel == Level.values().length) {
            game.setScreen(HighScoreScreen.getInstance(game));
            return;
        }
        engine.getSystem(LevelSystem.class).initializeLevel(Level.values()[currentLevel]);
    }

    public void show() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new InputProcessor(engine.getSystem(movementSystem.getClass())));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;
        if (elapsedTime > 5) {
            elapsedTime = 0;
            nextLevel();
        }
        engine.update(delta);
        game.camera.update();
    }
}
