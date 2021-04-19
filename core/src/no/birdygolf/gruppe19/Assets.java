
package no.birdygolf.gruppe19;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

// Defines all the textures saved inside "android -> assets"
// contains an assetManager that control which assets that are used
public class Assets {

    public static Sprite ball, obstacle, hole;

    public static Sprite loadSprite (Texture texture) {
        return new Sprite(texture);
    }

    public static void load() {
        ball = loadSprite(new Texture("sprites/golfball.png"));
        obstacle = loadSprite(new Texture("sprites/obstacle.png"));
        hole = loadSprite(new Texture("sprites/hole2.png"));
    }
}