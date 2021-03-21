package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

// Describing the velocity and the acceleration of the ball
public class MovementComponent implements Component {
    public Vector2 velocity = new Vector2();
    public Vector2 accel = new Vector2();

    // FROM SET PRESSED
    public Vector2 pressedPosition = new Vector2();
    public boolean wasPressed = false;
}