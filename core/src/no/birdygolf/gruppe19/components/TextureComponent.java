package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import no.birdygolf.gruppe19.Assets;

//Getting the ball texture
public class TextureComponent implements Component {

    private Texture component;
    private Sprite componentSprite;

    public TextureComponent(){
        this.component = Assets.ball;
        this.componentSprite = new Sprite(component);
        this.componentSprite.setScale(0.3f);
    }

    public Sprite getComponent() {
        return componentSprite;
    }
}
