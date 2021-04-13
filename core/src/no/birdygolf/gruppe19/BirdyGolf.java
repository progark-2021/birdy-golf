package no.birdygolf.gruppe19;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import no.birdygolf.gruppe19.api.FirebaseApi;
import no.birdygolf.gruppe19.screen.GameScreen;
import no.birdygolf.gruppe19.screen.PlayScreen;
import no.birdygolf.gruppe19.screen.TitleScreen;

public class BirdyGolf extends Game {
    public FirebaseApi firebaseApi;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public FitViewport viewport;

    public FreeTypeFontGenerator font;
    public TextureAtlas uiAtlas;
    public Skin skin;

    public BirdyGolf(FirebaseApi firebaseApi){
        super();
        this.firebaseApi = firebaseApi;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        //camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        Assets.load();
        viewport = new FitViewport(800, 480, camera);

        font = new FreeTypeFontGenerator(Gdx.files.internal("fonts/kenvector_future.ttf"));
        uiAtlas = new TextureAtlas(Gdx.files.internal("ui/uiPack.txt"));
        skin = new Skin();
        skin.addRegions(uiAtlas);

        setScreen(new PlayScreen(this));
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
