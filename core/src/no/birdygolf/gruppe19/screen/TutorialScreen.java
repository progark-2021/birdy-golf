package no.birdygolf.gruppe19.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import no.birdygolf.gruppe19.BirdyGolf;

import static no.birdygolf.gruppe19.screen.ScreenUtils.createInputListener;

public class TutorialScreen  extends ScreenAdapter {
    private static TutorialScreen instance;

    BirdyGolf game;

    FitViewport viewport;

    Stage stage;
    Table layout;
    Table tut_layout;

    Label title;
    Label.LabelStyle labelStyle;

    Label text;
    Label text1;
    Label text2;

    TextButton titleScreen;
    TextButton.TextButtonStyle textButtonStyle;

    FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter buttonParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    FreeTypeFontGenerator.FreeTypeFontParameter textParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont titleFont;
    BitmapFont buttonFont;
    BitmapFont textFont;
    ScrollPane scrollPane = new ScrollPane(new Table());

    Image dragFinger;


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
        text = new Label(
                "To play single game, \n" +
                        " \n" +
                        " \n" +
                        "yo \n" +
                        "giske \n" +
                        "katrine \n" +
                        "haldis \n", labelStyle2);
        //text 1 og text 2 kan fjernes
        /*
        text1 = new Label(" tekst til tutoral1 ", labelStyle2);
        text2 = new Label(" tekst til tutoral2 ", labelStyle2);


         */
        dragFinger = new Image(new SpriteDrawable(new Sprite(new Texture("tutorial/drag_finger.gif"))));
        //sound = new TextureRegion(new Texture("music/sound.png"));
        //dragFinger = new Image(new Texture("tutorial/One_finger_drag.png"));

        layout = new Table();
        //layout.setFillParent(true);
        layout.add(title);
        layout.row();
        layout.add(scrollPane).height(500).width(460).padBottom(15).padTop(15);

        //layout.add(text).width(10).pad(10);
        layout.row();




        layout.add(titleScreen).width(400).pad(10);
        layout.pad(10f);


        tut_layout = new Table();
        //tut_layout.setFillParent(true);
        tut_layout.add(text);
        tut_layout.row();
        /*
        tut_layout.add(text1);
        tut_layout.row();
        tut_layout.add(text2);
        tut_layout.row();

         */
        tut_layout.add(dragFinger);
        tut_layout.row();


        scrollPane.setActor(tut_layout);
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPane.setStyle(scrollPaneStyle);

        stage.addActor(layout);
        layout.setPosition(
                (viewport.getWorldWidth() - layout.getWidth()) / 2,
                (viewport.getWorldHeight() - layout.getHeight())/2
        );

        /*
        stage.addActor(layout2);
        layout2.setPosition(
                (viewport.getWorldWidth() - layout2.getWidth()) / 2,
                (viewport.getWorldHeight() - layout2.getHeight()) / 6
        );

         */

    

    }

}
