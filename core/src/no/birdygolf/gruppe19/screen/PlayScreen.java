package no.birdygolf.gruppe19.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.factory.WorldFactory;
import no.birdygolf.gruppe19.systems.MovementSystem;

public class PlayScreen extends ScreenAdapter {

    BirdyGolf game;

    FitViewport viewport;
    Stage stage;

    WorldFactory world;
    PooledEngine engine;

    public PlayScreen(BirdyGolf game) {
        this.game = game;
        engine = new PooledEngine();
        world = new WorldFactory(engine);

        engine.addSystem(new MovementSystem());

        world.create();
    }

    private void createUi() {
        viewport = new FitViewport(480, 800, game.camera);

        stage = new Stage(viewport);

    }
}
