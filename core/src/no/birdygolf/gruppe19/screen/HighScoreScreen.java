package no.birdygolf.gruppe19.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

import no.birdygolf.gruppe19.BirdyGolf;

public class HighScoreScreen extends ScreenAdapter {
    BirdyGolf game;

    FitViewport viewport;

    Stage stage;
    Table layout;

    Skin skin;
    TextureAtlas uiAtlas;

    Label title;
    Label.LabelStyle labelStyle;

    TextButton levelSelect;
    TextButton highScores;
    TextButton settings;
    TextButton.TextButtonStyle textButtonStyle;

    FreeTypeFontGenerator font = new FreeTypeFontGenerator(Gdx.files.internal("fonts/kenvector_future.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter buttonParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont titleFont;
    BitmapFont buttonFont;

    public HighScoreScreen(BirdyGolf game){
        this.game = game;


        viewport = new FitViewport(480, 800, game.camera);

        titleParameter.size = 72;
        titleFont = font.generateFont(titleParameter);

        buttonParameter.size = 36;
        buttonFont = font.generateFont(buttonParameter);

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        uiAtlas = new TextureAtlas(Gdx.files.internal("ui/uiPack.txt"));
        skin.addRegions(uiAtlas);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        textButtonStyle.up = skin.getDrawable("blue_button00");
        textButtonStyle.down = skin.getDrawable("blue_button01");
        levelSelect = new TextButton("go to next screen", textButtonStyle);

        levelSelect.pad(15);


        labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        title = new Label("Gamescreen", labelStyle);

        layout = new Table();
        layout.add(title);
        layout.row();
        layout.add(levelSelect).width(400).pad(10);
        layout.pad(10f);

        stage.addActor(layout);
        layout.setPosition(
                (viewport.getWorldWidth() - layout.getWidth()) / 2,
                (viewport.getWorldHeight() - layout.getHeight()) / 2
        );
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.8f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
}
