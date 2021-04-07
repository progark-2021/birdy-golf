package no.birdygolf.gruppe19.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.InputProcessor;
import no.birdygolf.gruppe19.factory.WorldFactory;
import no.birdygolf.gruppe19.systems.BoundsSystem;
import no.birdygolf.gruppe19.systems.MovementSystem;
import no.birdygolf.gruppe19.systems.RenderingSystem;

public class PlayScreen extends ScreenAdapter {

    BirdyGolf game;
    Stage stage;
    WorldFactory world;
    PooledEngine engine;
    InputMultiplexer inputMultiplexer;
    private MovementSystem movementSystem;

    public PlayScreen(BirdyGolf game) {
        this.game = game;
        engine = new PooledEngine();
        world = new WorldFactory(engine);
        stage = new Stage();
        this.movementSystem = new MovementSystem();


        engine.addSystem(movementSystem);
        engine.addSystem(new RenderingSystem(game.batch));
        engine.addSystem(new BoundsSystem());

        engine.getSystem(RenderingSystem.class).setProcessing(true);
        engine.getSystem(BoundsSystem.class).setProcessing(true);

        world.create();
    }

    public void show() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new InputProcessor(engine.getSystem(movementSystem.getClass())));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render (float delta) {
        show();
        engine.update(delta);
        game.camera.update();
    }
}
