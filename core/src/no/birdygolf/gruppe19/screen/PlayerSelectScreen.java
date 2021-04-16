package no.birdygolf.gruppe19.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;

import no.birdygolf.gruppe19.BirdyGolf;
import no.birdygolf.gruppe19.GameManager;

import static no.birdygolf.gruppe19.screen.ScreenUtils.createInputListener;

public class PlayerSelectScreen extends ScreenAdapter {
    private static final List<String> players = new ArrayList<>();
    private static final List<TextField> fields = new ArrayList<>();
    private static final int MAX_PLAYERS = 4;
    private static PlayerSelectScreen instance;

    BirdyGolf game;

    FitViewport viewport;

    Stage stage;
    Table layout;

    Label titleUpper;
    Label titleLower;
    Label.LabelStyle labelStyle;

    TextButton playGame;
    TextButton addPlayer;
    TextButtonStyle textButtonStyle;

    TextField.TextFieldStyle textFieldStyle;

    FreeTypeFontParameter titleParameter = new FreeTypeFontParameter();
    FreeTypeFontParameter buttonParameter = new FreeTypeFontParameter();
    BitmapFont titleFont;
    BitmapFont buttonFont;


    private PlayerSelectScreen(BirdyGolf game) {
        this.game = game;
    }

    public static PlayerSelectScreen getInstance(BirdyGolf game) {
        if (instance == null) {
            instance = new PlayerSelectScreen(game);
        }

        players.clear();
        players.add("");

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
        updatePlayers();
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
        playGame.pad(15);
        playGame.addListener(createInputListener(game, PlayScreen.getInstance(game)));


        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = buttonFont;
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = game.skin.getDrawable("yellow_panel");

        fields.clear();
        players.forEach(player -> fields.add(new TextField(player, textFieldStyle)));

        addPlayer = new TextButton("Add Player", textButtonStyle);
        addPlayer.pad(15);
        addPlayer.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                players.add("");
                hide();
                show();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        titleUpper = new Label("Select", labelStyle);
        titleLower = new Label("Players", labelStyle);


        layout = new Table();
        layout.add(titleUpper);
        layout.row();
        layout.add(titleLower);
        layout.row();

        fields.forEach(field -> {
            layout.add(field).width(400).height(50).pad(10);
            layout.row();
        });

        if (players.size() < MAX_PLAYERS) {
            layout.add(addPlayer).width(400).pad(10);
            layout.row();
        }
        layout.add(playGame).width(400).pad(10);
        layout.row();
        layout.pad(10f);

        stage.addActor(layout);
        layout.setPosition(
                (viewport.getWorldWidth() - layout.getWidth()) / 2,
                (viewport.getWorldHeight() - layout.getHeight()) / 2
        );
    }

    private void updatePlayers() {
        GameManager.INSTANCE.playerNames.clear();
        GameManager.INSTANCE.playerHits.clear();

        for (int i = 0; i < fields.size(); i++) {
            players.set(i, fields.get(i).getText());

            GameManager.INSTANCE.playerNames.add(players.get(i));
            GameManager.INSTANCE.playerHits.add(0);
        }
    }
}
