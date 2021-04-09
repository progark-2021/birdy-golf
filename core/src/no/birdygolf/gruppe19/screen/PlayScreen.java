package no.birdygolf.gruppe19.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.GameManager;
import no.birdygolf.gruppe19.factory.WorldFactory;
import no.birdygolf.gruppe19.levels.Level;
import no.birdygolf.gruppe19.systems.BoundsSystem;
import no.birdygolf.gruppe19.systems.LevelSystem;
import no.birdygolf.gruppe19.systems.RenderingSystem;

public class PlayScreen extends ScreenAdapter {

    BirdyGolf game;
    WorldFactory world;
    PooledEngine engine;
    private float elapsedTime = 0;

    public PlayScreen(BirdyGolf game) {
        this.game = game;
        engine = new PooledEngine();
        world = new WorldFactory(engine);

        //engine.addSystem(new MovementSystem());
        engine.addSystem(new RenderingSystem(game.batch));
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new LevelSystem(world));

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
