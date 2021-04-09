package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

// Describing the balls rotation
public class TransformComponent implements Component {
    //initial
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation = 0.0f;

    //set pressed
    public Vector2 pressedPosition = new Vector2();
    public boolean wasPressed = false;

    // set player
    public Vector2 startpoint;

    // trajectorypoint
    public Vector2 trajectoryPoints;

    // dragged
    public final Vector2 currentPos = new Vector2();
    public Vector2 velocityVector = new Vector2();
    public float distance, angle;

}



