package no.birdygolf.gruppe19;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import no.birdygolf.gruppe19.screen.TitleScreen;

public class BirdyGolf extends Game {
    public SpriteBatch batch;
    public OrthographicCamera camera;

    public FreeTypeFontGenerator font;
    public TextureAtlas uiAtlas;
    public Skin skin;


    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);

        font = new FreeTypeFontGenerator(Gdx.files.internal("fonts/kenvector_future.ttf"));
        uiAtlas = new TextureAtlas(Gdx.files.internal("ui/uiPack.txt"));
        skin = new Skin();
        skin.addRegions(uiAtlas);

        setScreen(TitleScreen.getInstance(this));
    }

    @Override
    public void render() {
        clearBackground();
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        uiAtlas.dispose();
    }

    private void clearBackground() {
        Gdx.gl.glClearColor(0.4f, 0.8f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
