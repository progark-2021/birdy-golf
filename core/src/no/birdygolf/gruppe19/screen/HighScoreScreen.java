
package no.birdygolf.gruppe19.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.List;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.api.FirebaseStatus;
import no.birdygolf.gruppe19.api.ScoreDto;
import no.birdygolf.gruppe19.api.ScoreObserver;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import static no.birdygolf.gruppe19.screen.ScreenUtils.createInputListener;

public class HighScoreScreen extends ScreenAdapter implements ScoreObserver {
    private static HighScoreScreen instance;
    private BirdyGolf game;
    private FitViewport viewport;
    private Stage stage;
    private Table layout;
    private Label.LabelStyle titleStyle;
    private TextButton titleScreen;
    private TextButtonStyle textButtonStyle;
    private FreeTypeFontParameter titleParameter = new FreeTypeFontParameter();
    private FreeTypeFontParameter buttonParameter = new FreeTypeFontParameter();
    private FreeTypeFontParameter listParameter = new FreeTypeFontParameter();
    private BitmapFont titleFont, buttonFont, listFont;
    private ScrollPane scrollPane = new ScrollPane(new Table());
    private Table highScoreTable;

    private HighScoreScreen(BirdyGolf game) {
        this.game = game;
    }

    public static HighScoreScreen getInstance(BirdyGolf game) {
        if (instance == null) {
            instance = new HighScoreScreen(game);
        }
        return instance;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.8f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        // Listen to the Firebase API
        game.firebaseApi.listen(this);

        // Initialize high score list
        listParameter.size = 30;
        listFont = game.font.generateFont(listParameter);
        game.firebaseApi.getScores();

        // Build UI
        createUi();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        game.firebaseApi.mute(this);
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        titleFont.dispose();
        buttonFont.dispose();
        listFont.dispose();
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
        titleScreen = new TextButton("Main Menu", textButtonStyle);
        titleScreen.pad(15);
        titleScreen.addListener(createInputListener(game, TitleScreen.getInstance(game)));

        titleStyle = new Label.LabelStyle();
        titleStyle.font = titleFont;

        layout = new Table();
        layout.add(new Label("High", titleStyle));
        layout.row();
        layout.add(new Label("Scores", titleStyle));
        layout.row();
        layout.add(scrollPane).height(500).width(460).padBottom(15).padTop(15);
        layout.row();
        layout.add(titleScreen).width(400).pad(10);
        layout.pad(10f);

        stage.addActor(layout);
        layout.setPosition(
                (viewport.getWorldWidth() - layout.getWidth()) / 2,
                (viewport.getWorldHeight() - layout.getHeight()) / 2
        );
    }

    /**
     * Method used to populate the high score list.
     * Called by {@link no.birdygolf.gruppe19.api.FirebaseApi} to populate the list when DB fetch completes.
     *
     * @param highScores High score list fetched from Firebase.
     * @param status     Indicates what status the high score list should render.
     */
    public void createHighScoreList(List<ScoreDto> highScores, FirebaseStatus status) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = listFont;

        highScoreTable = new Table();

        if (status == FirebaseStatus.LOADING) {
            highScoreTable.add(new Label("loading high scores...", labelStyle));
        } else if (status == FirebaseStatus.ERROR) {
            highScoreTable.add(new Label("Error fetching scores!", labelStyle));
        } else {
            highScoreTable.add(new Label("#", labelStyle));
            highScoreTable.add(new Label("Player", labelStyle)).padLeft(20).padRight(20);
            highScoreTable.add(new Label("Score", labelStyle));
            highScoreTable.row();
            int i = 1;
            for (ScoreDto highScore : highScores
            ) {
                highScoreTable.add(new Label(Integer.toString(i), labelStyle));
                highScoreTable.add(new Label(highScore.getPlayer(), labelStyle));
                highScoreTable.add(new Label(Long.toString(highScore.getScore()), labelStyle));
                highScoreTable.row();
                i++;
            }
        }

        highScoreTable.setWidth(460);
        scrollPane.setActor(highScoreTable);
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPane.setStyle(scrollPaneStyle);

    }

    @Override
    public void receiveUpdate(List<ScoreDto> highScores, FirebaseStatus status) {
        createHighScoreList(highScores, status);
    }
}
