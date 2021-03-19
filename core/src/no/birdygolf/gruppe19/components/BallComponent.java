package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class BallComponent implements Component {
    public CircleShape CIRCLE_SHAPE = new CircleShape();
    public float RADIUS = 6f;
    public float DENSITY = 0.5f;
    public float FRICTION = 0.4f;
    public float RESTITUTION = 0.6f;
}
