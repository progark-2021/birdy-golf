package no.birdygolf.gruppe19.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ScreenUtils {

    public static InputListener createInputListener(Game game, Screen screen){
        return new InputListener(){
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(screen);
            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };
    }

}
