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

public class TutorialScreen  extends ScreenAdapter {
    private static TutorialScreen instance;

    BirdyGolf game;

    FitViewport viewport;

    Stage stage;
    Table layout;

    Label title;
    Label.LabelStyle labelStyle;

    Label text;

    TextButton titleScreen;
    TextButton.TextButtonStyle textButtonStyle;

    FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter buttonParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter textParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont titleFont;
    BitmapFont buttonFont;
    BitmapFont textFont;

    private TutorialScreen(BirdyGolf game) {
        this.game = game;
    }

    public static TutorialScreen getInstance(BirdyGolf game) {
        if (instance == null) {
            instance = new TutorialScreen(game);
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

        textParameter.size = 30;
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

        labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        title = new Label("Tutorial", labelStyle);

        Label.LabelStyle labelStyle2 = new Label.LabelStyle();
        labelStyle2.font = textFont;
        text = new Label(" tekst til tutoral ", labelStyle2);
        //text.setWrap(true);
        //text.setWidth(400);

        layout = new Table();
        layout.add(title);
        layout.row();
        layout.add(text);
        //layout.add(text).width(10).pad(10);
        layout.row();
        layout.add(titleScreen).width(400).pad(10);
        layout.pad(10f);

        stage.addActor(layout);
        layout.setPosition(
                (viewport.getWorldWidth() - layout.getWidth()) / 2,
                (viewport.getWorldHeight() - layout.getHeight()) / 2
        );
    }

}
