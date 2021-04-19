package no.birdygolf.gruppe19.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

import no.birdygolf.gruppe19.BirdyGolf;

import static no.birdygolf.gruppe19.screen.ScreenUtils.createInputListener;

public class SummaryScreen extends ScreenAdapter {
    private static SummaryScreen instance;
    private BirdyGolf game;
    private FitViewport viewport;
    private Stage stage;
    private Table layout;
    private Label title;
    private Label.LabelStyle labelStyle;
    private TextButton highScores;
    private TextButton.TextButtonStyle textButtonStyle;
    private FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator.FreeTypeFontParameter buttonParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont titleFont, buttonFont;

    private SummaryScreen(BirdyGolf game) {
        this.game = game;
    }

    public static SummaryScreen getInstance(BirdyGolf game) {
        if (instance == null) {
            instance = new SummaryScreen(game);
        }
        return instance;
    }

    @Override
    public void render(float delta) {
        stage.draw();
    }

    @Override
    public void dispose() {
        buttonFont.dispose();
        titleFont.dispose();
        stage.dispose();
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
        highScores = new TextButton("High Scores", textButtonStyle);
        highScores.pad(15);
        highScores.addListener(createInputListener(game, HighScoreScreen.getInstance(game)));

        labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        title = new Label("Summary", labelStyle);

        layout = new Table();
        layout.add(title);
        layout.row();
        layout.add(highScores).width(400).pad(10);
        layout.pad(10f);

        stage.addActor(layout);
        layout.setPosition(
                (viewport.getWorldWidth() - layout.getWidth()) / 2,
                (viewport.getWorldHeight() - layout.getHeight()) / 2
        );
    }
}
