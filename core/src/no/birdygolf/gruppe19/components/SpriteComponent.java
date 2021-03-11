package no.birdygolf.gruppe19.ball;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BallEntity {

    private Sprite ball;

    public BallEntity() {
        ball = new Sprite(new Texture("golfball.png"));

    }

    public Sprite getSprite() {
        return ball;
    }

}
