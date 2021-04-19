package no.birdygolf.gruppe19.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;

import no.birdygolf.gruppe19.BirdyGolf;

import static no.birdygolf.gruppe19.screen.ScreenUtils.createInputListener;

public class TitleScreen extends ScreenAdapter {
    private static TitleScreen instance;
    private BirdyGolf game;
    private FitViewport viewport;
    private Stage stage;
    private Table layout;
    private Label titleUpper, titleLower;
    private Label.LabelStyle labelStyle;
    private TextButton playGame, highScores, tutorial;
    private TextButtonStyle textButtonStyle;
    private FreeTypeFontParameter titleParameter = new FreeTypeFontParameter();
    private FreeTypeFontParameter buttonParameter = new FreeTypeFontParameter();
    private BitmapFont titleFont, buttonFont;

    private TitleScreen(BirdyGolf game) {
        this.game = game;
    }

    public static TitleScreen getInstance(BirdyGolf game) {
        if (instance == null) {
            instance = new TitleScreen(game);
        }
        return instance;
    }

    @Override
    public void dispose() {
        stage.dispose();
        titleFont.dispose();
        buttonFont.dispose();
    }

    @Override
    public void render(float delta) {
        stage.draw();
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

        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = buttonFont;
        textButtonStyle.up = game.skin.getDrawable("blue_button00");
        textButtonStyle.down = game.skin.getDrawable("blue_button01");
        playGame = new TextButton("Play Game", textButtonStyle);
        tutorial = new TextButton("Tutorial", textButtonStyle);
        highScores = new TextButton("High Scores", textButtonStyle);
        playGame.pad(15);
        tutorial.pad(15);
        highScores.pad(15);

        playGame.addListener(createInputListener(game, PlayerSelectScreen.getInstance(game)));
        tutorial.addListener(createInputListener(game, TutorialScreen.getInstance(game)));
        highScores.addListener(createInputListener(game, HighScoreScreen.getInstance(game)));

        labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        titleUpper = new Label("Birdy", labelStyle);
        titleLower = new Label("Golf", labelStyle);

        layout = new Table();
        layout.add(titleUpper);
        layout.row();
        layout.add(titleLower);
        layout.row();
        layout.add(playGame).width(400).pad(10);
        layout.row();
        layout.add(tutorial).width(400).pad(10);
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
