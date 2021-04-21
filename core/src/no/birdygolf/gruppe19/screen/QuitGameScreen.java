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

public class QuitGameScreen  extends ScreenAdapter {
    private static QuitGameScreen instance;

    BirdyGolf game;

    FitViewport viewport;

    Stage stage;
    Table layout;


    Label titleUpper, titleLower;

    Label text_info;

    TextButton titleScreen;
    TextButton.TextButtonStyle textButtonStyle;

    FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter buttonParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter textParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont titleFont;
    BitmapFont buttonFont;
    BitmapFont textFont;

    private QuitGameScreen(BirdyGolf game) {
        this.game = game;
    }

    public static QuitGameScreen getInstance(BirdyGolf game) {
        if (instance == null) {
            instance = new QuitGameScreen(game);
        }
        return instance;
    }

    @Override
    public void render(float delta) {

        stage.draw();
       // stage.act(delta);
    }

    @Override
    public void dispose() {
        buttonFont.dispose();
        titleFont.dispose();
        textFont.dispose();
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

        textParameter.size = 20;
        textFont = game.font.generateFont(textParameter);

        buttonParameter.size = 36;
        buttonFont = game.font.generateFont(buttonParameter);

        stage = new Stage(viewport);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        textButtonStyle.up = game.skin.getDrawable("blue_button00");
        textButtonStyle.down = game.skin.getDrawable("blue_button01");
        titleScreen = new TextButton("Back to Menu", textButtonStyle);
        titleScreen.pad(15);
        titleScreen.addListener(createInputListener(game, TitleScreen.getInstance(game)));

        Label.LabelStyle labelStyle_title = new Label.LabelStyle();
        Label.LabelStyle labelStyle_text = new Label.LabelStyle();
        Label.LabelStyle labelStyle_subTitle = new Label.LabelStyle();
        labelStyle_title.font = titleFont;
        labelStyle_text.font = textFont;

        titleUpper = new Label("Game", labelStyle_title);
        titleLower = new Label("Over", labelStyle_title);


        text_info = new Label(
                "You have ended this game! \n\n" +
                        "To play again, go to menu \n" +
                        "screen and start over :)",labelStyle_text);

        layout = new Table();
        layout.add(titleUpper);
        layout.row();
        layout.add(titleLower);
        layout.row();
        layout.add(text_info);
        layout.row();
        layout.add(titleScreen).width(400).padTop(70);
        layout.pad(10f);

        stage.addActor(layout);
        layout.setPosition(
                (viewport.getWorldWidth() - layout.getWidth()) / 2,
                (viewport.getWorldHeight() - layout.getHeight())/2
        );


    }

}

