package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {

    private Sprite ball;

    public SpriteComponent() {
        ball = new Sprite(new Texture("golfball.png"));
    }

    public Sprite getSprite() {
        return ball;
    }

}
