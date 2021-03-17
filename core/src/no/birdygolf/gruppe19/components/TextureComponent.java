package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

import no.birdygolf.gruppe19.Assets;

//Getting the ball texture
public class TextureComponent implements Component {

    private Assets asset;
    private Texture component;

    public TextureComponent(){
        this.component = asset.ball;

    }



}
