package no.birdygolf.gruppe19.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import static no.birdygolf.gruppe19.screen.ScreenUtils.createInputListener;

public class TutorialScreen  extends ScreenAdapter {
    private static TutorialScreen instance;
    private BirdyGolf game;
    private FitViewport viewport;
    private Stage stage;
    private Table layout, scrollPane_layout;
    private Image dragFinger;
    private Label title;

    private Label text_single_player, text_multi_player, text_info, text_rules, text_level_switch;
    private Label single_player, multi_player, rules, info;

    private TextButton titleScreen;
    private TextButton.TextButtonStyle textButtonStyle;

    private ScrollPane scrollPane = new ScrollPane(new Table());
    private FreeTypeFontParameter titleParameter = new FreeTypeFontParameter();
    private FreeTypeFontParameter buttonParameter = new FreeTypeFontParameter();
    private FreeTypeFontParameter subTitleParameter = new FreeTypeFontParameter();
    private FreeTypeFontParameter textParameter = new FreeTypeFontParameter();
    private BitmapFont titleFont;
    private BitmapFont buttonFont;
    private BitmapFont subTitleFont;
    private BitmapFont textFont;

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
        stage.act(delta);
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

        subTitleParameter.size = 30;
        subTitleFont=game.font.generateFont(subTitleParameter);

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
        labelStyle_subTitle.font = subTitleFont;

        title = new Label("Tutorial", labelStyle_title);

        single_player = new Label("\nSingle player: \n", labelStyle_subTitle);
        multi_player = new Label("\nMulti player: \n", labelStyle_subTitle);
        rules = new Label("\nWinning rules\n", labelStyle_subTitle);
        info = new Label("\nGame Info: \n", labelStyle_subTitle);

        text_info = new Label(
                "This is a golf game! \n\n" +
                "The game has four levels, \n" +
                "To hit the golf ball\n" +
                "drag your finger and release",labelStyle_text);

        text_level_switch = new Label(
                        "if the ball enters the hole or \n" +
                        "all five hits are used, \n" +
                        "the next level will show. \n\n", labelStyle_text);

        text_rules = new Label(
                         "To win the game, the ball must\n" +
                         "enter the hole with as few\n" +
                         "hits as possible!\n", labelStyle_text);

        text_single_player = new Label(
                " \n" +
                        "press 'play game' button \n" +
                        "enter your name and press play\n", labelStyle_text);
        text_multi_player = new Label(
                "press 'play game' button \n" +
                      "enter player 1. name \n" +
                      "press 'add player' and enter \n" +
                      "player 2. name\n\n" +
                      "The players plays every second\n" +
                      "time in each levels,\n", labelStyle_text);


        dragFinger = new Image(new SpriteDrawable(new Sprite(new Texture("tutorial/drag_finger.png"))));

        layout = new Table();
        layout.add(title);
        layout.row();
        layout.add(scrollPane).height(500).width(460).padBottom(15).padTop(5);
        layout.row();
        layout.add(titleScreen).width(400).pad(10);
        layout.pad(10f);


        scrollPane_layout = new Table();
        scrollPane_layout.add(info);
        scrollPane_layout.row();
        scrollPane_layout.add(text_info);
        scrollPane_layout.row();

        scrollPane_layout.add(dragFinger);
        scrollPane_layout.row();

        scrollPane_layout.add(text_level_switch);
        scrollPane_layout.row();
        scrollPane_layout.add(rules);
        scrollPane_layout.row();
        scrollPane_layout.add(text_rules);
        scrollPane_layout.row();

        scrollPane_layout.add(single_player);
        scrollPane_layout.row();
        scrollPane_layout.add(text_single_player);
        scrollPane_layout.row();
        scrollPane_layout.add(multi_player);
        scrollPane_layout.row();
        scrollPane_layout.add(text_multi_player);
        scrollPane_layout.row();


        scrollPane.setActor(scrollPane_layout);
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPane.setStyle(scrollPaneStyle);

        stage.addActor(layout);
        layout.setPosition(
                (viewport.getWorldWidth() - layout.getWidth()) / 2,
                (viewport.getWorldHeight() - layout.getHeight())/2
        );
    

    }

}
