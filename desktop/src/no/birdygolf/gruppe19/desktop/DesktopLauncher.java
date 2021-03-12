package no.birdygolf.gruppe19.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import no.birdygolf.gruppe19.BirdyGolf;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 800;
		config.resizable = false;
		new LwjglApplication(new BirdyGolf(), config);
	}
}
