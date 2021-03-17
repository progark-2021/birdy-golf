package no.birdygolf.gruppe19.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.factory.WorldFactory;
import no.birdygolf.gruppe19.systems.MovementSystem;
import static no.birdygolf.gruppe19.screen.ScreenUtils.createInputListener;


public class GameScreen extends ScreenAdapter {
    private static GameScreen instance;

    BirdyGolf game;

    FitViewport viewport;

    Stage stage;
    Table layout;

    Label title;
    Label.LabelStyle labelStyle;

    TextButton levelSelect;
    TextButton.TextButtonStyle textButtonStyle;

    FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter buttonParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont titleFont;
    BitmapFont buttonFont;

    public GameScreen(BirdyGolf game) {
        this.game = game;
    }

    public static GameScreen getInstance(BirdyGolf game) {
        if (instance == null) {
            instance = new GameScreen(game);
        }
        return instance;
    }

    @Override
    public void render(float delta) {
        stage.draw();

    }

    @Override
    public void dispose() {


    }


    @Override
    public void show() {
        createUi();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        dispose();
    }

    private void createUi() {
        viewport = new FitViewport(480, 800, game.camera);

        titleParameter.size = 72;
        titleFont = game.font.generateFont(titleParameter);

        buttonParameter.size = 36;
        buttonFont = game.font.generateFont(buttonParameter);

        stage = new Stage(viewport);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        textButtonStyle.up = game.skin.getDrawable("blue_button00");
        textButtonStyle.down = game.skin.getDrawable("blue_button01");
        levelSelect = new TextButton("next screen", textButtonStyle);
        levelSelect.pad(15);
        levelSelect.addListener(createInputListener(game, SummaryScreen.getInstance(game)));

        labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        title = new Label("Game", labelStyle);

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
}

