package no.birdygolf.gruppe19.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.factory.WorldFactory;
import no.birdygolf.gruppe19.systems.MovementSystem;

public class GameScreen extends ScreenAdapter {

    BirdyGolf game;
    WorldFactory world;
    PooledEngine engine;

    public GameScreen(BirdyGolf game) {
        this.game = game;
        engine = new PooledEngine();
        world = new WorldFactory(engine);

        engine.addSystem(new MovementSystem());

        world.create();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
    }

}
