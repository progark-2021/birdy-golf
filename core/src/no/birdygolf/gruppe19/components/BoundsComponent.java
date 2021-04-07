package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.CircleShape;

// Containing the shape of the ball
public class BoundsComponent implements Component {
    //public static final CircleShape CIRCLE_SHAPE = new CircleShape();
    public Vector3 pos = new Vector3(); //updating the position to the bounds 
    public Rectangle bounds = new Rectangle();
}
