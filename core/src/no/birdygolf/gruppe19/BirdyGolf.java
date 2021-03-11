package no.birdygolf.gruppe19;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.birdygolf.gruppe19.screen.TitleScreen;

public class BirdyGolf extends Game {
    public SpriteBatch batch;
    public OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        setScreen(new TitleScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
