
package no.birdygolf.gruppe19;

import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Sprite;

// Defines all the textures saved inside "android -> assets"
// contains an assetManager that control which assets that are used
public class Assets {

    public static Texture ball;

    public static Texture loadTexture (String file) {
		return new Texture(file);
	}

    public static void load() {
        ball = loadTexture("sprites/golfball.png");
    }
}