package no.birdygolf.gruppe19.screen;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.InputProcessor;
import no.birdygolf.gruppe19.GameManager;
import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.factory.WorldFactory;
import no.birdygolf.gruppe19.levels.Level;
import no.birdygolf.gruppe19.systems.LevelSystem;
import no.birdygolf.gruppe19.systems.MovementSystem;
import no.birdygolf.gruppe19.systems.RenderingSystem;

public class PlayScreen extends ScreenAdapter {

    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private float accumulator = 0;

    BirdyGolf game;
    Stage stage;
    WorldFactory factory;
    World world;
    PooledEngine engine;
    InputMultiplexer inputMultiplexer;
    private MovementSystem movementSystem;
    private float elapsedTime = 0;

    public PlayScreen(BirdyGolf game) {
        this.game = game;
        this.engine = new PooledEngine();
        this.world = new World(new Vector2(0, 0), true);
        this.factory = new WorldFactory(engine, world);
        this.stage = new Stage();
        this.movementSystem = new MovementSystem();


        engine.addSystem(movementSystem);
        engine.addSystem(new RenderingSystem(game.batch));
        engine.addSystem(new LevelSystem(factory));
        engine.getSystem(MovementSystem.class).setProcessing(true);
        engine.getSystem(RenderingSystem.class).setProcessing(true);

        engine.getSystem(LevelSystem.class).initializeLevel(Level.LEVEL_1);
        engine.getSystem(MovementSystem.class).refreshGolfball();
    }

    private void nextLevel() {
        int currentLevel = ++GameManager.INSTANCE.currentLevel;
        if (currentLevel == Level.values().length) {
            game.setScreen(HighScoreScreen.getInstance(game));
            return;
        }
        engine.getSystem(LevelSystem.class).initializeLevel(Level.values()[currentLevel]);
        engine.getSystem(MovementSystem.class).refreshGolfball();
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

        engine.update(delta);
        debugRenderer.render(world, game.camera.combined);
        game.camera.update();

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

        System.out.println(engine.getEntitiesFor(Family.all(BallComponent.class).get()).get(0).getComponent(PhysicsComponent.class).fixture.getBody().getLinearVelocity());
    }
}
