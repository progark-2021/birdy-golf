package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

// Describing the balls rotation
public class TransformComponent implements Component {
    public final Vector2 currentPos = new Vector2();
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation = 0.0f;

    // FROM SET PRESSED
    public Vector2 pressedPosition = new Vector2();
    public boolean wasPressed = false;
}



