package no.birdygolf.gruppe19;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.screen.TitleScreen;

public class BirdyGolf extends Game {
    public SpriteBatch batch;
    public OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        PhysicsComponent test = new PhysicsComponent();
        System.out.println(test.getBody());
        System.out.println("hallo");
        System.out.println("posisjon: "+test.getPosition());
        System.out.println("figur: " + test.getShape());
        setScreen(new TitleScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
