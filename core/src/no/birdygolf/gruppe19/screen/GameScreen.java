package no.birdygolf.gruppe19.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import no.birdygolf.gruppe19.BirdyGolf;

public class GameScreen extends ScreenAdapter {

    BirdyGolf game;

    public GameScreen(BirdyGolf game) {
        this.game = game;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
    }

}
